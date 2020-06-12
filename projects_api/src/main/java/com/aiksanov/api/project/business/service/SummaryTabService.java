package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.ProjectURLs;
import com.aiksanov.api.project.data.entity.StatusReport;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.data.repository.StatusReportRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.web.DTO.summary.SummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SummaryTabService {
    private final ProjectGeneralService generalService;
    private final ProjectURLsRepository urlsRepo;
    private final StatusReportRepository reportRepo;
    private final ActionsService actionsService;
    private final RisksService risksService;


    public SummaryDTO getSummaryDTO(Integer projectID){
        Project projectInfo = this.generalService.getProjectGeneralInfo(projectID);
        ProjectURLs urls = this.urlsRepo.findById(projectID).orElseGet(ProjectURLs::new);
        StatusReport report = this.reportRepo.findById(projectID).orElseGet(StatusReport::new);
        int activeActionsAmount = this.actionsService.getActiveActions(projectID);
        int activeRisksAmount = this.risksService.getActiveRisks(projectID);
        return new SummaryDTO(projectInfo, urls, report, activeRisksAmount, activeActionsAmount);
    }
}