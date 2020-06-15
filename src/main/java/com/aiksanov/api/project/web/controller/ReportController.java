package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.PptGenerationService;
import com.aiksanov.api.project.business.service.ReportService;
import com.aiksanov.api.project.exceptions.TooMuchFilesException;
import com.aiksanov.api.project.web.DTO.Base64ImageDTO;
import com.aiksanov.api.project.web.DTO.reports.ReportSnapshot;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ReportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;
    private final PptGenerationService pptGenerationService;

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

    @GetMapping("/{projectId}/tabs/report/images")
    public List<Base64ImageDTO> getReportImages(@PathVariable int projectId) {
        LOGGER.info("GET /api/projects/{}/tabs/report/images", projectId);
        return this.reportService.getReportImages(projectId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{projectId}/tabs/report/images")
    public void saveReportImages(@PathVariable int projectId, @RequestParam("files")MultipartFile[] files)
            throws IOException, TooMuchFilesException
    {
        LOGGER.info("POST /api/projects/{}/tabs/report/images", projectId);
        this.reportService. saveImages(projectId, files);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{projectId}/tabs/report/images/{filename}")
    public void deleteReportImages(@PathVariable int projectId, @PathVariable String filename) {
        LOGGER.info("DELETE /api/projects/{}/tabs/report/images Filename: {}", projectId, filename);
        this.reportService.deleteImage(projectId, filename);
    }
}