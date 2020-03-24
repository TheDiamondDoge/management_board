package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.RisksService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.web.DTO.ErrorExportDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksTabDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tabs")
public class RisksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RisksController.class);

    private RisksService risksService;

    @Autowired
    public RisksController(RisksService risksService) {
        this.risksService = risksService;
    }

    @GetMapping("/risks")
    public RisksTabDTO getRisks(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/risks", projectId);
        return this.risksService.getRisksTab(projectId);
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
    public List<ErrorExportDTO> uploadExcelFile(@PathVariable int projectId, @RequestParam("file") MultipartFile file) throws IOException, RestTemplateException {
        LOGGER.info("POST /api/projects/{}/tabs/risks Filename: {}", projectId, file.getOriginalFilename());
        return this.risksService.processRiskFile(file, projectId);
    }

    @GetMapping(value = "/risksFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getRisksFile(@PathVariable int projectId) throws IOException, RestTemplateException {
        LOGGER.info("GET /api/projects/{}/tabs/risksFile", projectId);
        return this.risksService.getRisksAsFileToUser(projectId);
    }

    @GetMapping(value = "/risks/lastUploaded", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<Resource> getLastUpdatedFile(@PathVariable int projectId) throws IOException {
        LOGGER.info("GET /api/projects/{}/tabs/risks/lastUploaded", projectId);
        return this.risksService.getLastUpdatedFile(projectId);
    }
}
