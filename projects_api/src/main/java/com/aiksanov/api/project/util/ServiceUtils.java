package com.aiksanov.api.project.util;

import com.aiksanov.api.project.data.entity.Product;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExist;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import com.aiksanov.api.project.web.DTO.risks.RisksFromFileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import javax.xml.ws.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public List<Integer> generateRandomDataset() {
        List<Integer> numbers = new ArrayList<>();
        for (int j = 0; j < 15; j++) {
            numbers.add(getRandomInt() / 10000000);
        }

        return numbers;
    }

    private int getRandomInt() {
        Random rand = new Random();
        return Math.abs(rand.nextInt());
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
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExist::new);
        return project.getName();
    }

    public String getProjectsBD(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExist::new);
        String bd = "";
        try {
            bd = project.getProduct().getDivision();
        } catch (Exception e) {
            return bd;
        }

        return bd;
    }

    public ResponseEntity sendFileToService(MultipartFile file, String url) throws IOException, RestTemplateException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response;
        try {
            response = restTemplate.postForEntity(url, requestEntity, RisksFromFileDTO.class);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }
        return response;
    }
}
