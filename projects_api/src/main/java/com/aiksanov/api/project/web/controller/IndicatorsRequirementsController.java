package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.IndicatorsService;
import com.aiksanov.api.project.web.DTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/indicators")
public class IndicatorsRequirementsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsRequirementsController.class);
    private IndicatorsService indicatorsService;

    @Autowired
    public IndicatorsRequirementsController(IndicatorsService indicatorsService) {
        this.indicatorsService = indicatorsService;
    }

    @GetMapping("/requirements/{id}")
    public IndicatorsReqDTO getRequirements(@PathVariable int id) {
        LOGGER.info("GET /api/indicators/requirements/{}", id);
        return indicatorsService.getRqDTO(id);
    }

    @GetMapping("/milestones/{id}")
    public List<MilestoneIndKpiDTO> getMilestones(@PathVariable int id) {
        return this.indicatorsService.getKpiMilestones(id);
    }

    @GetMapping("/dr4/{id}")
    public IndicatorsDr4KpiDTO getDr4Kpi(@PathVariable int id) {
        return this.indicatorsService.getDr4Kpi(id);
    }

    @GetMapping("/quality/{id}")
    public QualityIndicatorsTableDTO getQuality(@PathVariable int id) {
        return this.indicatorsService.getQuality(id);
    }
}
