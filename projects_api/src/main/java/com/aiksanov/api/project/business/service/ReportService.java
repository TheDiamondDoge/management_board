package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.entity.pk.StatusReportPK;
import com.aiksanov.api.project.data.repository.ReportSnapshotRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.PptConfigurationData;
import com.aiksanov.api.project.web.DTO.RequirementsDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
import com.aiksanov.api.project.web.DTO.reports.ReportSnapshot;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectGeneral;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ProjectGeneralService generalService;
    private StatusReportRepository reportRepository;
    private ReportSnapshotRepository snapshotRepository;
    private MilestoneService milestoneService;
    private RequirementsService requirementsService;
    private RisksService risksService;
    private HealthService indicatorsService;

    @Autowired
    public ReportService(ProjectGeneralService generalService, StatusReportRepository reportRepository,
                         ReportSnapshotRepository snapshotRepository, MilestoneService milestoneService,
                         RequirementsService requirementsService, RisksService risksService, HealthService indicatorsService) {
        this.generalService = generalService;
        this.reportRepository = reportRepository;
        this.snapshotRepository = snapshotRepository;
        this.milestoneService = milestoneService;
        this.requirementsService = requirementsService;
        this.risksService = risksService;
        this.indicatorsService = indicatorsService;
    }

    public ReportTabDTO getReportTab(int projectId) {
        Project project = this.generalService.getProjectGeneralInfo(projectId);
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

    public PptConfigurationData getUserSnapshot(int projectId, int reportId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StatusReportSnapshot report = this.snapshotRepository.findById(new StatusReportPK(projectId, reportId))
                .orElseThrow(() ->new Exception("No snapshot found"));

        return mapper.readValue(report.getShapshotJson(), PptConfigurationData.class);
    }

    @Transactional
    public void saveReport(int projectId, UserReportsSaveDTO dto) throws Exception {
        StatusReport report = getStatusReportObj(projectId, dto);
        this.reportRepository.save(report);

        StatusReportSnapshot snapshot = createPptReportSnapshot(projectId);
        this.snapshotRepository.save(snapshot);
    }

    private StatusReportSnapshot createPptReportSnapshot(int projectId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StatusReportSnapshot snapshot = new StatusReportSnapshot();
        PptConfigurationData currentState = getDataForPptCreation(projectId);

        StatusReportPK pk = new StatusReportPK(projectId, lastStatusReportId(projectId) + 1);
        snapshot.setPk(pk);

        String json = mapper.writeValueAsString(currentState);
        snapshot.setTimestamp(new Date());
        snapshot.setShapshotJson(json);

        return snapshot;
    }

    public PptConfigurationData getDataForPptCreation(int projectId) {
        ProjectGeneral projectGeneral = generalService.getProjectGeneralObj(projectId);
        List<MilestoneDTO> milestones = milestoneService.getTimelineMilestones(projectId);
        List<RisksDTO> risks = risksService.getProjectRisks(projectId);
        List<RequirementsDTO> requirements = requirementsService.getJiraRequirements();
        HealthIndicatorsDTO indicators = indicatorsService.getHealthIndicators(projectId);
        UserReportsDTO reports = getUserReports(projectId);

        String executionSummary = reports.getSummary();
        String projectDetails = reports.getDetails();
        List<String> flags = new ArrayList<>();
        flags.add(reports.getRed());
        flags.add(reports.getOrange());
        flags.add(reports.getGreen());

        return new PptConfigurationData()
                .setGeneral(projectGeneral)
                .setMilestones(milestones)
                .setRisks(risks)
                .setRequirements(requirements)
                .setIndicators(indicators)
                .setExecutionSummary(executionSummary)
                .setProjectDetails(projectDetails)
                .setFlags(flags);
    }

    private StatusReport getStatusReportObj(int projectId, UserReportsSaveDTO dto) {
        StatusReport report = new StatusReport();
        report.setProjectId(projectId);
        report.setExecutiveSummary(dto.getSummary());
        report.setRedFlag(dto.getRed());
        report.setOrangeFlag(dto.getOrange());
        report.setGreenFlag(dto.getGreen());
        report.setDetails(dto.getDetails());
        return report;
    }

    private int lastStatusReportId(int projectId) {
        StatusReportSnapshot report = this.snapshotRepository.getLastSnapshotByProjectId(projectId).orElseGet(StatusReportSnapshot::new);
        if (Objects.nonNull(report.getPk())) {
            return report.getPk().getReportId();
        } else {
            return 0;
        }
    }

    public List<ReportSnapshot> getListOfSnapshots(int projectId) {
        List<StatusReportSnapshot> reports = this.snapshotRepository.findAllByPk_ProjectId(projectId).orElseGet(ArrayList::new);
        List<ReportSnapshot> snapshots = reports.stream()
                .map(report -> (new ReportSnapshot(report.getPk().getReportId(), report.getTimestamp())))
                .sorted()
                .collect(Collectors.toList());
        if (snapshots.size() > 0) {
            snapshots.remove(0);
        }

        return snapshots;
    }
}
