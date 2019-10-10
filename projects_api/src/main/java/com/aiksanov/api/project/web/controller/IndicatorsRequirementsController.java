package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.web.DTO.IndicatorsReqDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indicators")
public class IndicatorsRequirementsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsRequirementsController.class);

    @GetMapping("/requirements/{id}")
    public IndicatorsReqDTO getRequirements(@PathVariable int id) {
        LOGGER.info("GET /api/indicators/requirements/{}", id);
        return new IndicatorsReqDTO();
    }
}
