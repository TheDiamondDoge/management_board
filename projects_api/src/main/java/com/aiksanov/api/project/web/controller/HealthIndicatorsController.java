package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.HealthService;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.web.DTO.HealthIndicatorsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class HealthIndicatorsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthIndicatorsController.class);
    private HealthService healthService;

    @Autowired
    public HealthIndicatorsController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/{projectID}")
    public HealthIndicatorsDTO getHealthIndicators(@PathVariable Integer projectID){
        LOGGER.info("GET /api/health/{}", projectID);
        return this.healthService.getHealthIndicators(projectID);
    }

    @PutMapping("/{projectID}")
    public void addHealthIndicator(@PathVariable Integer projectID, @RequestBody HealthIndicatorsDTO indicatorDTOs){
        this.healthService.saveHealthIndicators(projectID, indicatorDTOs);
    }
}
