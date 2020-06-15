package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.RequirementsService;
import com.aiksanov.api.project.web.DTO.RequirementsDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects/{projectId}/tabs")
public class RequirementsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequirementsController.class);
    private final RequirementsService requirementsService;

    @GetMapping("/rqs")
    public List<RequirementsDTO> getJiraRqs(@PathVariable int projectId) {
        LOGGER.info("/api/projects/{}/tabs/rqs", projectId);
        return this.requirementsService.getJiraRequirements();
    }
}
