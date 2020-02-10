package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.CostService;
import com.aiksanov.api.project.web.DTO.cost.CostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
