package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.DefectsService;
import com.aiksanov.api.project.web.DTO.backlog.BacklogDefectsChartDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects/{id}/tabs")
public class DefectsController {
    public static final Logger LOGGER = LoggerFactory.getLogger(DefectsController.class);
    private final DefectsService defectsService;

    @GetMapping("/defects/chart")
    public BacklogDefectsChartDTO getChartData(@PathVariable int id) {
        LOGGER.info("GET /api/projects/{}/tabs/defects/chart", id);
        return this.defectsService.getChartData(id);
    }
}
