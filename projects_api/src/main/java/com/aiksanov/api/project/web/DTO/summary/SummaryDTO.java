package com.aiksanov.api.project.web.DTO.summary;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
public class SummaryDTO {
    private String productName;
    private String projectDescription;
    private String projectManager;
    private String businessLineManager;
    private String productLineManager;
    private ProjectStates projectState;
    private String projectRigor;
    private String charter;
    private String orBusinessPlan;
    private String updatedBusinessPlan;
    private String drChecklist;
    private String lessonsLearned;
    private String sponsor;
    private String businessDivision;
    private String businessUnit;
    private String productLine;
    private WorkspaceStatus workspaceState;
    private String projectType;
    private String oemPartner;
    private Date disabledTime;
    private String executiveSummary;
    private String redFlag;
    private String orangeFlag;
    private String greenFlag;
    private String details;
    private String collabSite;
    private String epmPwaSite;
    private String documentationRepo;
    private String defectsReportSite;
    private int activeRisks;
    private int activeActions;
    private Date epmLastSavedDate;
    private Date pwsLastUpdatedDate;
    private String pwsLastUpdatedBy;
    private boolean isEpm;


    public SummaryDTO(Project projectInfo, ProjectURLs urls, StatusReport report, int activeRisks, int activeActions) {
        if (Objects.nonNull(projectInfo)) {
            projectInfoMapping(projectInfo);
        }

        if (Objects.nonNull(urls)) {
            urlsMapping(urls);
        }

        if (Objects.nonNull(report)) {
            reportMapping(report);
        }

        this.activeRisks = activeRisks;
        this.activeActions = activeActions;
    }

    private void projectInfoMapping(Project projectInfo) {
        this.projectManager = projectInfo.getManager();
        this.projectState = projectInfo.getState();
        this.projectRigor = projectInfo.getRigor().getLabel();
        if (Objects.nonNull(projectInfo.getType())) {
            this.projectType = projectInfo.getType().getValue();
        }
        this.isEpm = projectInfo.isEpm();

        Product product = projectInfo.getProduct();
        if (Objects.nonNull(product)) {
            productMapping(product);
        }

        ProjectAdditionalInfo additionalInfo = projectInfo.getAdditionalInfo();
        if (Objects.nonNull(additionalInfo)) {
            additionalInfoMapping(additionalInfo);
        }

        WorkspaceInfo workspaceInfo = projectInfo.getWorkspaceInfo();
        if (Objects.nonNull(workspaceInfo)) {
            workspaceInfoMapping(workspaceInfo);
        }
    }

    private void productMapping(Product product) {
        this.productName = product.getName();
        this.productLineManager = product.getManager();
        this.businessDivision = product.getDivision();
        this.businessUnit = product.getBusinessUnit();
        this.productLine = product.getProductLine();
    }

    private void additionalInfoMapping(ProjectAdditionalInfo additionalInfo) {
        this.projectDescription = additionalInfo.getDescription();
        this.businessLineManager = additionalInfo.getBusinessLineManager();
        this.sponsor = additionalInfo.getSponsor();
        this.oemPartner = additionalInfo.getOemPartner();
    }

    private void urlsMapping(ProjectURLs urls) {
        this.charter = urls.getCharter();
        this.orBusinessPlan = urls.getOrBusinessPlan();
        this.updatedBusinessPlan = urls.getUpdatedBusinessPlan();
        this.drChecklist = urls.getTailoredChecklist();
        this.lessonsLearned = urls.getLessonsLearned();
        this.collabSite = urls.getCollabUrl();
        this.epmPwaSite = urls.getPwaUrl();
        this.documentationRepo = urls.getDocumentsRepoUrl();
        this.defectsReportSite = urls.getDefectsUrl();
    }

    private void workspaceInfoMapping(WorkspaceInfo workspaceInfo) {
        this.workspaceState = workspaceInfo.getStatus();
        this.pwsLastUpdatedBy = workspaceInfo.getModifiedBy();
        this.pwsLastUpdatedDate = workspaceInfo.getModified();
    }

    private void reportMapping(StatusReport report) {
        this.executiveSummary = report.getExecutiveSummary();
        this.redFlag = report.getRedFlag();
        this.orangeFlag = report.getOrangeFlag();
        this.greenFlag = report.getGreenFlag();
        this.details = report.getDetails();
    }
}
