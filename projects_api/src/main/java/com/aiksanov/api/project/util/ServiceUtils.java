package com.aiksanov.api.project.util;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ServiceUtils {
    private MilestoneRepository milestoneRepository;
    private GeneralRepository generalRepository;

    @Autowired
    public ServiceUtils(MilestoneRepository milestoneRepository, GeneralRepository generalRepository) {
        this.milestoneRepository = milestoneRepository;
        this.generalRepository = generalRepository;
    }

    public boolean isDr1Exists(int projectID) {
        return milestoneRepository.existsById(new MilestonePK(projectID, MilestoneLabels.DR1.getLabel()));
    }

    public boolean isProjectExist(int projectID) {
        return this.generalRepository.existsById(projectID);
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public Date getCurrentDate() {
        return new Date();
    }

    public HttpHeaders getFileDownloadHeaders(String filename) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }

    public String getProjectName(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        return project.getName();
    }

    public String getProjectsBD(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        String bd = "";
        try {
            bd = project.getProduct().getDivision();
        } catch (Exception e) {
            return bd;
        }

        return bd;
    }

    public ByteArrayResource getDataFile(String url, Object object) throws IOException, RestTemplateException {
        RestTemplate template = new RestTemplate();
        ResponseEntity<ByteArrayResource> response;
        try {
            response = template.postForEntity(url, object, ByteArrayResource.class);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }

        return response.getBody();
    }

    public ResponseEntity sendFileToService(MultipartFile file, String url, Class<?> expectedClass) throws IOException, RestTemplateException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response;
        try {
            response = restTemplate.postForEntity(url, requestEntity, expectedClass);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }
        return response;
    }

    public String saveFile(MultipartFile file, String saveName, String storagePath) throws IOException {
        byte[] bytes = file.getBytes();
        String fullPath = storagePath + File.separator + saveName;
        Path path = Paths.get(fullPath);
        Files.write(path, bytes);
        return path.toString();
    }

    public ResponseEntity<Resource> giveFileToUser(String desiredFilename, String filepath) throws IOException {
        ByteArrayResource reader = new ByteArrayResource(Files.readAllBytes(Paths.get(filepath)));
        HttpHeaders header = this.getFileDownloadHeaders(desiredFilename);
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(reader);
    }

    public ResponseEntity<Resource> giveFileToUser(String desiredFilename, ByteArrayResource arrayResource) {
        HttpHeaders header = this.getFileDownloadHeaders(desiredFilename);
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(arrayResource);
    }

    public String dateToDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public String dateToString(Date date) {
        if (Objects.isNull(date)) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(date);
    }

    public String whitespaceToUnderscore(String str) {
        return str.replaceAll("\\s+", "_");
    }
}
