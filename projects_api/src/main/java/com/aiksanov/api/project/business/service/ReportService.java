package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.entity.pk.StatusReportPK;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.util.enums.ReportTypes;
import com.aiksanov.api.project.web.DTO.reports.ReportSnapshot;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsMinimalDTO;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksMinimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private ProjectGeneralService projectService;
    private StatusReportRepository reportRepository;

    @Autowired
    public ReportService(ProjectGeneralService projectService, StatusReportRepository reportRepository) {
        this.projectService = projectService;
        this.reportRepository = reportRepository;
    }

    public ReportTabDTO getReportTab(int projectId) {
        Project project = this.projectService.getProjectGeneralInfo(projectId);
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
        StatusReport report = this.reportRepository.getLastReportByProjectId(projectId).orElseGet(StatusReport::new);
        return new UserReportsDTO(report);
    }

    public UserReportsDTO getUserSnapshot(int projectId, int reportId) {
        StatusReport report = this.reportRepository.findById(new StatusReportPK(projectId, reportId))
                .orElseGet(StatusReport::new);
        return new UserReportsDTO(report);
    }

    @Transactional
    public void saveReport(int projectId, UserReportsSaveDTO dto) {
        StatusReport report = getStatusReportObj(projectId, dto);
        this.reportRepository.save(report);
    }

    private StatusReport getStatusReportObj(int projectId, UserReportsSaveDTO dto) {
        int lastReportId = lastStatusReportId(projectId);
        StatusReport report = new StatusReport();
        StatusReportPK pk = new StatusReportPK(projectId, lastReportId + 1);
        report.setPk(pk);
        report.setTimestamp(new Date());
        report.setExecutiveSummary(dto.getSummary());
        report.setRedFlag(dto.getRed());
        report.setOrangeFlag(dto.getOrange());
        report.setGreenFlag(dto.getGreen());
        report.setDetails(dto.getDetails());
        return report;
    }

    private int lastStatusReportId(int projectId) {
        StatusReport report = this.reportRepository.getLastReportByProjectId(projectId).orElseGet(StatusReport::new);
        if (Objects.nonNull(report.getPk())) {
            return report.getPk().getReportId();
        } else {
            return 0;
        }
    }

    public List<ReportSnapshot> getListOfSnapshots(int projectId) {
        List<StatusReport> reports = this.reportRepository.findAllByPk_ProjectId(projectId).orElseGet(ArrayList::new);
        List<ReportSnapshot> snapshots = reports.stream()
                .map(report -> (new ReportSnapshot(report.getPk().getReportId(), report.getTimestamp())))
                .sorted()
                .collect(Collectors.toList());
        if (snapshots.size() > 0) {
            snapshots.remove(0);
        }

        return snapshots;
    }

    public List<StatusReport> getAllReports(int projectId) {
        return this.reportRepository.findAllByPk_ProjectId(projectId).orElseGet(ArrayList::new);
    }
}
