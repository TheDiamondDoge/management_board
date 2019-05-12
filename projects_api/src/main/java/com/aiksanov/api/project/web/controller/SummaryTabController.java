package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.SummaryTabService;
import com.aiksanov.api.project.web.DTO.SummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{id}/tabs")
public class SummaryTabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SummaryTabController.class);
    private SummaryTabService summaryTabService;

    @Autowired
    public SummaryTabController(SummaryTabService summaryTabService) {
        this.summaryTabService = summaryTabService;
    }

    @GetMapping("/summary")
    public SummaryDTO getSummaryTabData(@PathVariable Integer id){
        LOGGER.info("GET /api/projects/{}/tabs/summary", id);
        return this.summaryTabService.getSummaryDTO(id);
    }
}
