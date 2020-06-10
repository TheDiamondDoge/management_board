package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.ReportSnapshotInfoRepository;
import com.aiksanov.api.project.data.repository.ReportSnapshotRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.PptExportTypes;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.PptConfigurationData;
import com.aiksanov.api.project.web.DTO.RequirementsDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
import com.aiksanov.api.project.web.DTO.reports.PptImageFile;
import com.aiksanov.api.project.web.DTO.reports.ReportSnapshot;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectGeneral;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PptGenerationService {
    @Value("${ppt.generator.custom}")
    private String CUSTOM_URL;

    @Value("${ppt.generator.indicators}")
    private String INDICATORS_URL;

    @Value("${ppt.generator.review}")
    private String REVIEW_URL;

    @Value("${reportImages.file.storage}")
    private String REPORT_IMGS_PATH;

    private final String POSTFIX = ".pptx";
    private final ProjectGeneralService projectGeneralService;
    private final MilestoneService milestoneService;
    private final RisksService risksService;
    private final RequirementsService requirementsService;
    private final HealthService indicatorsService;
    private final StatusReportRepository reportRepository;
    private final ReportSnapshotRepository snapshotRepository;
    private final ReportSnapshotInfoRepository snapshotInfoRepository;

    public PptGenerationService(ProjectGeneralService projectGeneralService, MilestoneService milestoneService,
                                RisksService risksService, RequirementsService requirementsService,
                                HealthService indicatorsService, StatusReportRepository reportRepository,
                                ReportSnapshotRepository snapshotRepository,
                                ReportSnapshotInfoRepository snapshotInfoRepository) {
        this.projectGeneralService = projectGeneralService;
        this.milestoneService = milestoneService;
        this.risksService = risksService;
        this.requirementsService = requirementsService;
        this.indicatorsService = indicatorsService;
        this.reportRepository = reportRepository;
        this.snapshotRepository = snapshotRepository;
        this.snapshotInfoRepository = snapshotInfoRepository;
    }

    public ResponseEntity<Resource> getPptFile(int projectId, PptExportTypes type) throws Exception {
        return getPptFile(projectId, type, null);
    }

    public ResponseEntity<Resource> getPptFile(int projectId, PptExportTypes type, Integer reportId) throws Exception {
        PptConfigurationData dataToSend;
        ByteArrayResource resource;
        String filename;
        Project project = this.projectGeneralService.getProjectGeneralInfo(projectId);
        if (Objects.isNull(reportId)) {
            dataToSend = getDataForPptCreation(projectId);
            resource = getPptFromService(dataToSend, type);
            filename = Utils.projectNameDecorator(project.getName()) + "_" + type.name().toLowerCase() +"_report" + POSTFIX;
        } else {
            StatusReportSnapshotInfo info = this.snapshotInfoRepository.findById(reportId).orElseGet(StatusReportSnapshotInfo::new);
            resource = getUserSnapshot(reportId);
            filename = Utils.projectNameDecorator(project.getName()) + "_" + Utils.dateToString(info.getCreatedOn()) + POSTFIX;
        }

        return Utils.giveFileToUser(filename, resource);
    }

    public PptConfigurationData getDataForPptCreation(int projectId) throws IOException {
        ProjectGeneral projectGeneral = getProjectGeneralObj(projectId);
        List<MilestoneDTO> milestones = milestoneService.getTimelineMilestones(projectId);
        List<RisksDTO> risks = risksService.getProjectRisks(projectId);
        List<RequirementsDTO> requirements = requirementsService.getJiraRequirements();
        HealthIndicatorsDTO indicators = indicatorsService.getHealthIndicators(projectId);
        UserReportsDTO reports = getUserReports(projectId);
        List<PptImageFile> imageFiles = getImages(projectId);

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
                .setFlags(flags)
                .setImages(imageFiles);
    }

    private List<PptImageFile> getImages(int projectId) throws IOException {
        String imageFolderPath = REPORT_IMGS_PATH + "/" + projectId;
        List<Path> filePaths = Files.walk(Paths.get(imageFolderPath))
                .filter(Files::isRegularFile)
                .map(Path::toAbsolutePath)
                .collect(Collectors.toList());

        List<PptImageFile> imageFiles = filePaths.stream().map(path -> {
            try {
                byte[] bytes = Files.readAllBytes(path);
                String filename = path.getName(path.getNameCount() - 1).toString();
                return new PptImageFile(filename, bytes);
            } catch (IOException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return imageFiles;
    }

    private UserReportsDTO getUserReports(int projectId) {
        StatusReport report = this.reportRepository.findById(projectId).orElseGet(StatusReport::new);
        return new UserReportsDTO(report);
    }

    public ByteArrayResource getUserSnapshot(int id) throws Exception {
        StatusReportSnapshot snapshot = this.snapshotRepository.findById(id).orElseThrow(Exception::new);
        byte[] blob = snapshot.getPptSnapshot();
        return new ByteArrayResource(blob);
    }

    private ByteArrayResource getPptFromService(PptConfigurationData data, PptExportTypes type) throws IOException, RestTemplateException {
        RestTemplate restTemplate = new RestTemplate();
        String url = getUrlForPpt(type);
        ResponseEntity<ByteArrayResource> response;
        try {
            response = restTemplate.postForEntity(url, data, ByteArrayResource.class);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }

        return response.getBody();
    }

    private String getUrlForPpt(PptExportTypes type) {
        switch (type) {
            case INDICATORS:
                return INDICATORS_URL;
            case REVIEW:
                return REVIEW_URL;
            case CUSTOM:
            default:
                return CUSTOM_URL;
        }
    }

    public List<ReportSnapshot> getListOfSnapshots(int projectId) {
        List<StatusReportSnapshotInfo> reports = this.snapshotInfoRepository.getLastSnapshotsInfoByProjectId(projectId);
        List<ReportSnapshot> snapshots = reports.stream()
                .map(report -> (new ReportSnapshot(report.getId(), report.getCreatedOn())))
                .sorted()
                .collect(Collectors.toList());
        if (snapshots.size() > 0) {
            snapshots.remove(snapshots.size() - 1);
        }

        return snapshots;
    }

    public ByteArrayResource createPptReportSnapshot(int projectId) throws Exception {
        PptConfigurationData data = getDataForPptCreation(projectId);
        return getPptFromService(data, PptExportTypes.CUSTOM);
    }

    private ProjectGeneral getProjectGeneralObj(int projectId) {
        Project project = this.projectGeneralService.getProjectGeneralInfo(projectId);
        WorkspaceInfo workspaceInfo = project.getWorkspaceInfo();
        Date updated = null;
        if (Objects.nonNull(workspaceInfo)) {
            updated = workspaceInfo.getModified();
        }
        return new ProjectGeneral(project.getName(), project.getManager(), "http://www.google.com", updated);
    }
}