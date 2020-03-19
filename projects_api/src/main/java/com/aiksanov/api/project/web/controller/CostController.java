package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.CostService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.web.DTO.cost.CostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/projects/{projectId}/tabs")
public class CostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CostController.class);
    private CostService costService;

    @Autowired
    public CostController(CostService costService) {
        this.costService = costService;
    }

    @GetMapping("/cost")
    public CostDTO getCostTabData(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/cost", projectId);
        return this.costService.getCostData(projectId);
    }

    @PostMapping("/cost")
    public void uploadCost(@PathVariable int projectId, @RequestParam("file") MultipartFile file) throws IOException, RestTemplateException {
        LOGGER.info("POST /api/projects/{}/tabs/cost Filename: {}", projectId, file.getOriginalFilename());
        this.costService.processCostFile(file, projectId);
    }
}
