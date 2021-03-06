package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.HealthService;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/health")
public class HealthIndicatorsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthIndicatorsController.class);
    private final HealthService healthService;

    @GetMapping("/{projectID}")
    public HealthIndicatorsDTO getHealthIndicators(@PathVariable Integer projectID) {
        LOGGER.info("GET /api/health/{}", projectID);
        return this.healthService.getHealthIndicators(projectID);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{projectID}")
    public void addHealthIndicator(@PathVariable Integer projectID, @RequestBody HealthIndicatorsDTO indicatorDTOs) {
        LOGGER.info("POST /api/health/{}", projectID);
        if (Objects.isNull(indicatorDTOs.getComments())) {
            this.healthService.saveHealthIndicators(indicatorDTOs, projectID);
        } else if (Objects.isNull(indicatorDTOs.getStatuses())) {
            this.healthService.saveHealthComments(indicatorDTOs, projectID);
        }
    }
}
