package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.RisksService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.ErrorExportDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tabs")
public class RisksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RisksController.class);

    private RisksService risksService;
    private ServiceUtils utils;

    @Autowired
    public RisksController(RisksService risksService, ServiceUtils utils) {
        this.risksService = risksService;
        this.utils = utils;
    }

    @GetMapping("/risks")
    public List<RisksDTO> getRisks(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/risks", projectId);
        return this.risksService.getProjectRisks(projectId);
    }

    @GetMapping("/risks/id")
    public List<String> getRisksIds(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/risks/id", projectId);
        return this.risksService.getListOfProjectsRisks(projectId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/risks")
    public void saveEditedRisk(@PathVariable int projectId, @RequestBody RisksDTO dto) {
        LOGGER.info("PUT /api/projects/{}/tabs/risks", projectId);
        this.risksService.saveEditedRisk(dto, projectId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/risks")
    public List<ErrorExportDTO> uploadExcelFile(@PathVariable int projectId, @RequestParam("file")MultipartFile file) throws IOException, RestTemplateException {
        LOGGER.info("POST /api/projects/{}/tabs/risks Filename: {}", projectId, file.getOriginalFilename());
        return this.risksService.processRiskFile(file, projectId);
    }

    @GetMapping(value = "/risksFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getRisksFile(@PathVariable int projectId) throws IOException {
        ByteArrayResource reader = new ByteArrayResource(Files.readAllBytes(Paths.get("D:\\git\\management_board_file_processors\\excellProcessors\\risksFileProcessor\\src\\main\\resources\\risks.pdf")));
        HttpHeaders header = utils.getFileDownloadHeaders("risk.pdf");
        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(reader);
    }
}
