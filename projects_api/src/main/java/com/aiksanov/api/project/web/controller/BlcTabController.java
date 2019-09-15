package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.BlcDashboardService;
import com.aiksanov.api.project.web.DTO.BlcDashboardDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{id}/tabs")
public class BlcTabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlcTabController.class);
    private BlcDashboardService blcService;

    @Autowired
    public BlcTabController(BlcDashboardService blcService) {
        this.blcService = blcService;
    }

    @GetMapping("/blc")
    public BlcDashboardDTO getBlcDTO(@PathVariable int id){
        LOGGER.info("GET /api/projects/{}/tabs/blc", id);
        return this.blcService.getBlcDTO(id);
    }
}
