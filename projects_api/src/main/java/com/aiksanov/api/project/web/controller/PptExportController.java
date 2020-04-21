package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.PptGenerationService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/export/ppt")
public class PptExportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PptExportController.class);
    private PptGenerationService service;

    @Autowired
    public PptExportController(PptGenerationService service) {
        this.service = service;
    }

    @GetMapping("/test/{projectId}")
    public void getFile(@PathVariable int projectId) throws IOException, RestTemplateException {
        LOGGER.info("GET /api/export/ppt/test");
        service.getPptFile(projectId);
    }
}
