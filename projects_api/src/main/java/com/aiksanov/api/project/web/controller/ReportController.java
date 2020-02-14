package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ReportService;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{projectId}/tabs")
public class ReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public ReportTabDTO getReportTabData(@PathVariable int projectId) {
        LOGGER.info("/api/projects/{}/tabs/report", projectId);
        return this.reportService.getReportTab(projectId);
    }

    @GetMapping("/user_reports")
    public UserReportsDTO getUserReports(@PathVariable int projectId) {
        LOGGER.info("/api/projects/{}/tabs/user_reports", projectId);
        return this.reportService.getUserReports(projectId);
    }
}
