package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.CostService;
import com.aiksanov.api.project.exceptions.FileNotSavedException;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.web.DTO.cost.CostDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/projects/{projectId}/tabs")
public class CostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CostController.class);
    private final CostService costService;

    @GetMapping("/cost")
    public CostDTO getCostTabData(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/cost", projectId);
        return this.costService.getCostData(projectId);
    }

    @PostMapping("/cost")
    public void uploadCost(@PathVariable int projectId, @RequestParam("files") MultipartFile[] file)
            throws IOException, RestTemplateException, FileNotSavedException {
        LOGGER.info("POST /api/projects/{}/tabs/cost Filename: {}", projectId, file[0].getOriginalFilename());
        this.costService.processCostFile(file[0], projectId);
    }

    @GetMapping(value = "/cost/lastUploaded", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getLastUpdatedFile(@PathVariable int projectId) throws IOException {
        LOGGER.info("GET /api/projects/{}/tabs/cost/cost/lastUploaded", projectId);
        return this.costService.getLastUpdatedFile(projectId);
    }
}
