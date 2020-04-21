package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.PptConfigurationData;
import com.aiksanov.api.project.web.DTO.RequirementsDTO;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
import com.aiksanov.api.project.web.DTO.reports.UserReportsDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectGeneral;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PptGenerationService {
    @Value("${ppt.generator.url}")
    private String URL;
    private ProjectGeneralService generalService;
    private MilestoneService milestoneService;
    private RisksService risksService;
    private RequirementsService requirementsService;
    private HealthService indicatorsService;
    private ReportService reportService;

    @Autowired
    public PptGenerationService(ProjectGeneralService generalService, MilestoneService milestoneService,
                                RisksService risksService, RequirementsService requirementsService,
                                HealthService indicatorsService, ReportService reportService) {
        this.generalService = generalService;
        this.milestoneService = milestoneService;
        this.risksService = risksService;
        this.requirementsService = requirementsService;
        this.indicatorsService = indicatorsService;
        this.reportService = reportService;
    }

    public void getPptFile(int projectId) throws IOException, RestTemplateException {
        PptConfigurationData dataToSend = getDataForPptCreation(projectId);
        getPptFromService(dataToSend);
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

    private void getPptFromService(PptConfigurationData data) throws IOException, RestTemplateException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ByteArrayResource> response;
        try {
            response = restTemplate.postForEntity(URL, data, ByteArrayResource.class);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }
        System.out.println(response.getBody());
    }
}
