package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.IndicatorsService;
import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.web.DTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/requirements/{id}")
    public void saveRequirements(@PathVariable int id, @RequestBody IndicatorsReqDTO rqs){
        LOGGER.info("POST /api/indicators/requirements/{}", id);
        this.indicatorsService.saveIndicatorsRqs(rqs, id);
    }

    @GetMapping("/milestones/{id}")
    public List<MilestoneIndKpiDTO> getMilestones(@PathVariable int id) {
        LOGGER.info("GET /api/indicators/milestones/{}", id);
        return this.indicatorsService.getKpiMilestones(id);
    }

    @GetMapping("/dr4/{id}")
    public IndicatorsDr4KpiDTO getDr4Kpi(@PathVariable int id) {
        LOGGER.info("GET /api/indicators/dr4/{}", id);
        return this.indicatorsService.getDr4Kpi(id);
    }

    @GetMapping("/quality/{id}")
    public QualityIndicatorsTableDTO getQuality(@PathVariable int id) {
        LOGGER.info("GET /api/indicators/quality/{}", id);
        return this.indicatorsService.getQuality(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/quality/{id}")
    public void saveQuality(@PathVariable int id, @RequestBody QualityIndicatorsTableDTO dto) {
        LOGGER.info("POST /api/indicators/quality/{}", id);
        this.indicatorsService.saveQuality(dto, id);
    }
}
