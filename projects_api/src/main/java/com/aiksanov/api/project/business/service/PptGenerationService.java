package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.util.enums.PptExportTypes;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.PptConfigurationData;
import com.aiksanov.api.project.web.DTO.RequirementsDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class PptGenerationService {
    @Value("${ppt.generator.custom}")
    private String CUSTOM_URL;

    @Value("${ppt.generator.indicators}")
    private String INDICATORS_URL;

    @Value("${ppt.generator.review}")
    private String REVIEW_URL;

    private final String POSTFIX = ".pptx";
    private final ProjectGeneralService generalService;
    private final MilestoneService milestoneService;
    private final RisksService risksService;
    private final RequirementsService requirementsService;
    private final HealthService indicatorsService;
    private final ReportService reportService;
    private final ServiceUtils serviceUtils;

    public PptGenerationService(ProjectGeneralService generalService, MilestoneService milestoneService,
                                RisksService risksService, RequirementsService requirementsService,
                                HealthService indicatorsService, ReportService reportService, ServiceUtils serviceUtils) {
        this.generalService = generalService;
        this.milestoneService = milestoneService;
        this.risksService = risksService;
        this.requirementsService = requirementsService;
        this.indicatorsService = indicatorsService;
        this.reportService = reportService;
        this.serviceUtils = serviceUtils;
    }

    public ResponseEntity<Resource> getPptFile(int projectId, PptExportTypes type) throws IOException, RestTemplateException {
        PptConfigurationData dataToSend = getDataForPptCreation(projectId);
        ByteArrayResource resource = getPptFromService(dataToSend, type);
        return serviceUtils.giveFileToUser(projectId + POSTFIX, resource);
    }

    private PptConfigurationData getDataForPptCreation(int projectId) {
        ProjectGeneral projectGeneral = generalService.getProjectGeneralObj(projectId);
        List<MilestoneDTO> milestones = milestoneService.getMilestoneDTOsByProjectID(projectId);
        List<RisksDTO> risks = risksService.getProjectRisks(projectId);
        List<RequirementsDTO> requirements = requirementsService.getJiraRequirements();
        HealthIndicatorsDTO indicators = indicatorsService.getHealthIndicators(projectId);

        UserReportsDTO reports = reportService.getUserReports(projectId);
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
}
