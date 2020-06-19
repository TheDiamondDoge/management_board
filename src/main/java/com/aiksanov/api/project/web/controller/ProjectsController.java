package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ProjectGeneralService;
import com.aiksanov.api.project.business.service.ProjectTableViewService;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import com.aiksanov.api.project.web.DTO.contrib.ContributingDTO;
import com.aiksanov.api.project.web.DTO.PWSTableViewDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectDefaultDataDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);
    private final ProjectGeneralService projectGeneralService;
    private final ProjectTableViewService service;

    @GetMapping("/{id}")
    public Project getProjectInfoById(@PathVariable Integer id) {
        LOGGER.info("GET /api/projects/{}", id);
        return this.projectGeneralService.getProjectGeneralInfo(id);
    }

    @GetMapping()
    public Iterable<Project> getAllProjectsInfo(@RequestParam(required = false) Boolean isEPM,
                                                @RequestParam(required = false) WorkspaceStatus status) {
        LOGGER.info("GET /api/projects?isEPM={}&status={}", isEPM, status);
        return this.service.getProjectsData(isEPM, status);
    }

    @GetMapping("/tableview")
    public List<PWSTableViewDTO> getProjectsTableView(@RequestParam(required = false) Boolean isEPM,
                                                      @RequestParam(required = false) String status) {
        LOGGER.info("GET /api/projects/tableview?isEPM={}&status={}", isEPM, status);
        return this.service.getProjectsListView(isEPM, status);
    }

    @GetMapping("/{id}/contrib")
    public List<ContributingDTO> getProjectsAvailableToContrib(@PathVariable int id){
        LOGGER.info("GET /api/projects/{}/contrib", id);
        return this.projectGeneralService.getContributableProjects(id);
    }

    @GetMapping("/{id}/defaults")
    public ProjectDefaultDataDTO getProjectDefaults(@PathVariable int id) {
        LOGGER.info("GET /api/projects/{}/defaults", id);
        return this.projectGeneralService.getProjectDefaults(id);
    }

    @GetMapping("/{id}/getContribFile")
    public @ResponseBody
    ResponseEntity<Resource> getContribExcelFile(@PathVariable int id) throws IOException, RestTemplateException {
        LOGGER.info("GET /api/projects/{}/getContribFile", id);
        return this.projectGeneralService.getContribExcelFile(id);
    }
}

