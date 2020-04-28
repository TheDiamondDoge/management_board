package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.util.enums.ReportTypes;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsMinimalDTO;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksMinimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private ProjectGeneralService projectService;
    private MilestoneService milestoneService;
    private HealthService healthService;
    private RisksService risksService;
    private StatusReportRepository reportRepository;

    @Autowired
    public ReportService(ProjectGeneralService projectService, MilestoneService milestoneService,
                         HealthService healthService, RisksService risksService,
                         StatusReportRepository reportRepository) {
        this.projectService = projectService;
        this.milestoneService = milestoneService;
        this.healthService = healthService;
        this.risksService = risksService;
        this.reportRepository = reportRepository;
    }

    public ReportTabDTO getReportTab(int projectId) {
        Project project = this.projectService.getProjectGeneralInfo(projectId);
        List<MilestoneDTO> milestones = this.milestoneService.getShownMilestonesByProjectID(projectId);
        HealthIndicators indicators = this.healthService.getLastHealthIndicators(projectId);
        List<Risk> risks = this.risksService.getRiskList(projectId);

        ReportTabDTO dto = new ReportTabDTO();
        if (Objects.nonNull(project)) {
            dto.setProjectName(project.getName());
            dto.setProjectManager(project.getManager());
            WorkspaceInfo info = project.getWorkspaceInfo();
            if (Objects.nonNull(info)) {
                dto.setUpdatedOn(info.getModified());
            }
        }

        return dto;
    }

    public UserReportsDTO getUserReports(int projectId) {
        StatusReport report = this.reportRepository.findById(projectId).orElseGet(StatusReport::new);
        return new UserReportsDTO(report);
    }

    @Transactional
    public void saveReport(int projectId, UserReportsSaveDTO dto) {
        StatusReport report = getStatusReportObj(projectId, dto);
        this.reportRepository.save(report);
    }

    private StatusReport getStatusReportObj(int projectId, UserReportsSaveDTO dto) {
        StatusReport report = this.reportRepository.findById(projectId).orElseGet(StatusReport::new);
        report.setProjectId(projectId);
        report.setExecutiveSummary(dto.getSummary());
        report.setRedFlag(dto.getRed());
        report.setOrangeFlag(dto.getOrange());
        report.setGreenFlag(dto.getGreen());
        report.setDetails(dto.getDetails());
        return report;
    }
}
