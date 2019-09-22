package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.ProjectURLs;
import com.aiksanov.api.project.data.entity.StatusReport;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.web.DTO.HealthIndicatorsDTO;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryTabService {
    private GeneralRepository generalRepo;
    private ProjectURLsRepository urlsRepo;
    private StatusReportRepository reportRepo;
    private MilestoneService milestoneService;
    private HealthService healthService;

    @Autowired
    public SummaryTabService(GeneralRepository generalRepo, ProjectURLsRepository urlsRepo,
                             StatusReportRepository reportRepo, MilestoneService milestoneService, HealthService healthService) {
        this.generalRepo = generalRepo;
        this.urlsRepo = urlsRepo;
        this.reportRepo = reportRepo;
        this.milestoneService = milestoneService;
        this.healthService = healthService;
    }

    public SummaryDTO getSummaryDTO(Integer projectID){
        if (!this.generalRepo.existsById(projectID)){
            throw new RuntimeException("Project id doesn't exist");
        }
        Project projectInfo = this.generalRepo.findById(projectID).get();
        ProjectURLs urls = this.urlsRepo.findById(projectID).orElseGet(ProjectURLs::new);
        StatusReport report = this.reportRepo.findById(projectID).orElseGet(StatusReport::new);
        List<MilestoneDTO> milestones = this.milestoneService.getShownMilestonesByProjectID(projectID);
        HealthIndicatorsDTO healthIndicatorsDTO = this.healthService.getHealthIndicators(projectID);

        return new SummaryDTO(projectInfo, urls, report, milestones, healthIndicatorsDTO);
    }
}
