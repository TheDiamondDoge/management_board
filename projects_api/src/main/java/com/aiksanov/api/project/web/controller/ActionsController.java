package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ActionsService;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
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
public class ActionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionsController.class);
    private ActionsService actionsService;

    @Autowired
    public ActionsController(ActionsService actionsService) {
        this.actionsService = actionsService;
    }

    @GetMapping("/actions")
    public List<ActionDTO> getActionsByProjectId(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs", projectId);
        return this.actionsService.getAllActionsByProjectId(projectId);
    }
}
