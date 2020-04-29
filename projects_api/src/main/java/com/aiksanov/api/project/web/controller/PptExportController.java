package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.PptGenerationService;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.enums.PptExportTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/custom/{projectId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getCustomPptFile(@PathVariable int projectId, @RequestParam(required = false) Integer reportId) throws Exception {
        LOGGER.info("GET /api/export/ppt/custom/{}", projectId);
        return service.getPptFile(projectId, PptExportTypes.CUSTOM, reportId);
    }

    @GetMapping(value = "/indicators/{projectId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getFileIndicatorsPpt(@PathVariable int projectId) throws Exception {
        LOGGER.info("GET /api/export/ppt/indicators/{}", projectId);
        return service.getPptFile(projectId, PptExportTypes.INDICATORS);
    }

    @GetMapping(value = "/review/{projectId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    ResponseEntity<Resource> getFileReview(@PathVariable int projectId) throws Exception {
        LOGGER.info("GET /api/export/ppt/review/{}", projectId);
        return service.getPptFile(projectId, PptExportTypes.REVIEW);
    }
}
