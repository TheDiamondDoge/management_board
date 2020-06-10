package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.KpiService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsAmountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @GetMapping(value = "/{type}/issuesList/{projectId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getQualityIssuesFile(@PathVariable int projectId, @PathVariable String type) throws IOException, RestTemplateException {
        return this.kpiService.getIssuesListFile(projectId, type);
    }
}
