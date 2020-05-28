package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ReportSnapshotInfoRepository;
import com.aiksanov.api.project.data.repository.ReportSnapshotRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.web.DTO.reports.ReportTabDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;

@Service
public class ReportService {
    private PptGenerationService pptGenerationService;
    private StatusReportRepository reportRepository;
    private ReportSnapshotInfoRepository reportSnapshotInfoRepository;
    private GeneralRepository generalRepository;
    private ReportSnapshotRepository snapshotRepository;

    @Autowired
    public ReportService(PptGenerationService pptGenerationService, StatusReportRepository reportRepository,
                         ReportSnapshotRepository snapshotRepository, GeneralRepository generalRepository,
                         ReportSnapshotInfoRepository reportSnapshotInfoRepository)
    {
        this.reportSnapshotInfoRepository = reportSnapshotInfoRepository;
        this.pptGenerationService = pptGenerationService;
        this.reportRepository = reportRepository;
        this.snapshotRepository = snapshotRepository;
        this.generalRepository = generalRepository;
    }

    public ReportTabDTO getReportTab(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
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
    public void saveReport(int projectId, UserReportsSaveDTO dto) throws Exception {
        ByteArrayResource pptBinary = pptGenerationService.createPptReportSnapshot(projectId);
        StatusReportSnapshot snapshot = new StatusReportSnapshot(0, pptBinary.getByteArray());
        StatusReportSnapshot savedSnap = this.snapshotRepository.save(snapshot);
        int snapId = savedSnap.getId();

        StatusReportSnapshotInfo snapshotInfo = new StatusReportSnapshotInfo(snapId, projectId, new Date(), "Test User");
        this.reportSnapshotInfoRepository.save(snapshotInfo);

        StatusReport report = getStatusReportObj(projectId, dto);
        this.reportRepository.save(report);
//        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestRepSaver");
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
}
