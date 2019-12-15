package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.RisksService;
import com.aiksanov.api.project.data.entity.Risk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/tabs")
public class RisksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RisksController.class);
    private RisksService risksService;

    @Autowired
    public RisksController(RisksService risksService) {
        this.risksService = risksService;
    }

    @GetMapping("/risks")
    public List<Risk> getRisks(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/risks", projectId);
        return this.risksService.getProjectRisks(projectId);
    }
}
