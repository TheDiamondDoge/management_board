package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ActionsService;
import com.aiksanov.api.project.data.entity.ActionRelatedRisks;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionsController.class);
    private ActionsService actionsService;

    @Autowired
    public ActionsController(ActionsService actionsService) {
        this.actionsService = actionsService;
    }

    @GetMapping("/projects/{projectId}/tabs/actions")
    public List<ActionDTO> getActionsByProjectId(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs", projectId);
        return this.actionsService.getAllActionsByProjectId(projectId);
    }

    @GetMapping("/projects/{projectId}/tabs/arr")
    public Iterable<ActionRelatedRisks> getAllARR() {
        return this.actionsService.getAllARR();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/actions/{uid}")
    public void deleteActionByUID(@PathVariable int uid) {
        LOGGER.info("DELETE /api/actions/{}", uid);
        this.actionsService.deleteAction(uid);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/projects/{projectId}/tabs/actions")
    public void saveAction(@PathVariable int projectId, @RequestBody ActionDTO actionDTO) {
        LOGGER.info("POST /api/projects/{}/tabs", projectId);
        LOGGER.info(actionDTO.toString());
        this.actionsService.saveAction(projectId, actionDTO);
    }
}
