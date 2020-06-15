package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ActionsService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionsController.class);
    private final ActionsService actionsService;

    @GetMapping("/projects/{projectId}/tabs/actions")
    public List<ActionDTO> getActionsByProjectId(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs", projectId);
        return this.actionsService.getAllActionsByProjectId(projectId);
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
        this.actionsService.saveAction(projectId, actionDTO);
    }

    @GetMapping(value = "/actions/{projectId}/getFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getActionsFile(@PathVariable int projectId) throws IOException, RestTemplateException {
        LOGGER.info("GET /api/actions/{}/getFile", projectId);
        return this.actionsService.getActionsFile(projectId);
    }
}
