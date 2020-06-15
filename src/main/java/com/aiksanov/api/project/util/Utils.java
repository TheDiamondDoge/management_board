package com.aiksanov.api.project.util;

import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.time.LocalDate;
import java.util.*;

public class Utils {
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static HttpHeaders getFileDownloadHeaders(String filename) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }

    public static ByteArrayResource getDataFile(String url, Object object) throws IOException, RestTemplateException {
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

    public static ResponseEntity sendFileToService(MultipartFile file, String url, Class<?> expectedClass) throws IOException, RestTemplateException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return sendToServise(url, requestEntity, expectedClass);
    }

    public static ResponseEntity<?> sendToServise(String url, Object requestEntity, Class<?> expectedClass) throws IOException, RestTemplateException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> response;
        try {
            response = restTemplate.postForEntity(url, requestEntity, expectedClass);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }
        return response;
    }

    public static String saveFile(MultipartFile file, String saveName, String storagePath) throws IOException {
        byte[] bytes = file.getBytes();
        String fullPath = storagePath + File.separator + saveName;
        Path path = Paths.get(fullPath);
        Files.write(path, bytes);
        return path.toString();
    }

    public static ResponseEntity<Resource> giveFileToUser(String desiredFilename, String filepath) throws IOException {
        ByteArrayResource reader = new ByteArrayResource(Files.readAllBytes(Paths.get(filepath)));
        HttpHeaders header = getFileDownloadHeaders(desiredFilename);
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(reader);
    }

    public static ResponseEntity<Resource> giveFileToUser(String desiredFilename, ByteArrayResource arrayResource) {
        HttpHeaders header = getFileDownloadHeaders(desiredFilename);
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(arrayResource);
    }

    public static String dateToDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        if (Objects.isNull(date)) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String projectNameDecorator(String str) {
        return str.replaceAll("\\s+|\\.", "_");
    }

    public static String[][] listOfListsToStringArr(List<List<String>> data, int rowsAmount, int rowSize) {
        String[][] result = new String[rowsAmount][rowSize];
        for (int i = 0; i < rowsAmount; i++) {
            List<String> row = data.get(i);
            for (int j = 0; j < rowSize; j++) {
                result[i][j] = row.get(j);
            }
        }

        return result;
    }

    public static String getFileFormat(String filename) {
        String[] split = filename.split("\\.");
        return split[split.length - 1];
    }

    public static String fileToHtmlBase64Img(Path file) throws IOException {
        String filename = file.getName(file.getNameCount() - 1).toString();
        byte[] encodedBytes = FileUtils.readFileToByteArray(new File(String.valueOf(file)));
        String encodedString = Base64.getEncoder().encodeToString(encodedBytes);
        return "data:image/" + Utils.getFileFormat(filename) + ";base64, " + encodedString;
    }

    public static Date getSqlDateNow() {
        LocalDate localDate = LocalDate.now();
        return java.sql.Date.valueOf(localDate.toString());
    }
}