package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsMinimalDTO;
import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;
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
        HealthIndicators indicators = this.healthService.getHealthIndicators(projectId);
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

        if (Objects.nonNull(risks)) {
            List<RisksMinimalDTO> risksDtos = risks.stream().map(RisksMinimalDTO::new).collect(Collectors.toList());
            dto.setRisks(risksDtos);
        }

        dto.setMilestones(milestones);
        dto.setIndicators(new HealthIndicatorsMinimalDTO(indicators));

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
        String type = dto.getType();
        switch (type) {
            case "summary":
                report.setExecutiveSummary(dto.getData());
                return report;
            case "red":
                report.setRedFlag(dto.getData());
                return report;
            case "orange":
                report.setOrangeFlag(dto.getData());
                return report;
            case "green":
                report.setGreenFlag(dto.getData());
                return report;
            case "details":
                report.setDetails(dto.getData());
                return report;
        }
        return report;
    }
}
