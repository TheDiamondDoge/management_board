package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.BacklogService;
import com.aiksanov.api.project.web.DTO.backlog.BacklogDefectsChartDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{id}/tabs")
public class BacklogController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BacklogController.class);
    private final BacklogService backlogService;

    @GetMapping("/backlog/chart")
    public BacklogDefectsChartDTO getChartData(@PathVariable int id) {
        LOGGER.info("GET /api/projects/{}/tabs/backlog/chart", id);
        return this.backlogService.getChartData(id);
    }
}
