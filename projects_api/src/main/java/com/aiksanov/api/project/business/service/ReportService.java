package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.web.DTO.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsMinimalDTO;
import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksMinimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        StatusReport report = this.reportRepository.findById(projectId).orElseGet(StatusReport::new);
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
        dto.setSummary(report.getExecutiveSummary());
        dto.setRed(report.getRedFlag());
        dto.setOrange(report.getOrangeFlag());
        dto.setGreen(report.getGreenFlag());
        dto.setDetails(report.getDetails());

        return dto;
    }
}
