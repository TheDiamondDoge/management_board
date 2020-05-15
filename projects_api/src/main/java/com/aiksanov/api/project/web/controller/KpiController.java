package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.KpiService;
import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsAmountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kpi")
public class KpiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KpiController.class);
    private final KpiService kpiService;

    @Autowired
    public KpiController(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @GetMapping("/qualityIndicators/{projectId}")
    public QualityIndicatorsAmountDTO getQualityIndicatorsValues(@PathVariable int projectId) {
        LOGGER.info("GET /qualityIndicators/{}", projectId);
        return kpiService.getQualityIndicatorsValues(projectId);
    }
}
