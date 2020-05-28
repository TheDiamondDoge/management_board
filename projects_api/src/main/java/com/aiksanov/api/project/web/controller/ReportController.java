package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.PptGenerationService;
import com.aiksanov.api.project.business.service.ReportService;
import com.aiksanov.api.project.web.DTO.reports.ReportSnapshot;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    private ReportService reportService;
    private PptGenerationService pptGenerationService;

    @Autowired
    public ReportController(ReportService reportService, PptGenerationService pptGenerationService) {
        this.reportService = reportService;
        this.pptGenerationService = pptGenerationService;
    }

    @GetMapping("/{projectId}/tabs/report")
    public ReportTabDTO getReportTabData(@PathVariable int projectId) {
        LOGGER.info("/api/projects/{}/tabs/report", projectId);
        return this.reportService.getReportTab(projectId);
    }

    @GetMapping("/{projectId}/tabs/user_reports")
    public UserReportsDTO getUserReports(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/user_reports", projectId);
        return this.reportService.getUserReports(projectId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{projectId}/tabs/user_reports")
    public void saveReportOrFlag(@PathVariable int projectId, @RequestBody UserReportsSaveDTO dto) throws Exception {
        LOGGER.info("POST /api/projects/{}/tabs/user_reports", projectId);
        this.reportService.saveReport(projectId, dto);
    }

    @GetMapping("/{projectId}/tabs/snapshots_info")
    public List<ReportSnapshot> getSnapshotsInfo(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/snapshots_info", projectId);
        return this.pptGenerationService.getListOfSnapshots(projectId);
    }
}
