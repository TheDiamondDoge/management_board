package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.ProjectURLs;
import com.aiksanov.api.project.data.entity.StatusReport;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.web.DTO.summary.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SummaryTabService {
    private GeneralRepository generalRepo;
    private ProjectURLsRepository urlsRepo;
    private StatusReportRepository reportRepo;
    private ActionsService actionsService;
    private RisksService risksService;

    @Autowired
    public SummaryTabService(GeneralRepository generalRepo, ProjectURLsRepository urlsRepo, StatusReportRepository reportRepo,
                             ActionsService actionsService, RisksService risksService) {
        this.generalRepo = generalRepo;
        this.urlsRepo = urlsRepo;
        this.reportRepo = reportRepo;
        this.actionsService = actionsService;
        this.risksService = risksService;
    }

    public SummaryDTO getSummaryDTO(Integer projectID){
        Project projectInfo = this.generalRepo.findById(projectID)
                .orElseThrow(() -> new RuntimeException("Project id doesn't exist"));
        ProjectURLs urls = this.urlsRepo.findById(projectID).orElseGet(ProjectURLs::new);
        StatusReport report = this.reportRepo.findById(projectID).orElseGet(StatusReport::new);
        int activeActionsAmount = this.actionsService.getActiveActions(projectID);
        int activeRisksAmount = this.risksService.getActiveRisks(projectID);
        return new SummaryDTO(projectInfo, urls, report, activeRisksAmount, activeActionsAmount);
    }
}
