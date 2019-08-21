package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.*;

import java.util.Date;
import java.util.Objects;

public class SummaryDTO {
    private String projectName;
    private String projectDescription;
    private String projectManager;
    private String businessLineManager;
    private String productLineManager;
    private String projectState;
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
    private String workspaceState;
    private String projectType;
    private String oemPartner;
    private Date disabledTime;
    private String executiveSummary;
    private String executiveActions;
    private String collabSite;
    private String epmPwaSite;
    private String documentationRepo;
    private String defectsReportSite;
    private int activeRisks;
    private Date epmLastSavedDate;
    private Date pwsLastUpdatedDate;
    private String pwsLastUpdatedBy;
    private boolean isEpm;


    public SummaryDTO(Project projectInfo, ProjectURLs urls, StatusReport report) {
        if (Objects.nonNull(projectInfo)) {
            projectInfoMapping(projectInfo);
        }

        if (Objects.nonNull(urls)) {
            urlsMapping(urls);
        }

        if (Objects.nonNull(report)) {
            reportMapping(report);
        }
    }

    private void projectInfoMapping(Project projectInfo) {
        this.projectName = projectInfo.getName();
        this.projectManager = projectInfo.getManager();
        this.projectState = projectInfo.getState();
        this.projectRigor = projectInfo.getRigor();
        this.projectType = projectInfo.getType();
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
        this.executiveActions = report.getActionsNeeded();
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getBusinessLineManager() {
        return businessLineManager;
    }

    public String getProductLineManager() {
        return productLineManager;
    }

    public String getProjectState() {
        return projectState;
    }

    public String getProjectRigor() {
        return projectRigor;
    }

    public String getCharter() {
        return charter;
    }

    public String getOrBusinessPlan() {
        return orBusinessPlan;
    }

    public String getUpdatedBusinessPlan() {
        return updatedBusinessPlan;
    }

    public String getDrChecklist() {
        return drChecklist;
    }

    public String getLessonsLearned() {
        return lessonsLearned;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getBusinessDivision() {
        return businessDivision;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public String getProductLine() {
        return productLine;
    }

    public String getWorkspaceState() {
        return workspaceState;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getOemPartner() {
        return oemPartner;
    }

    public Date getDisabledTime() {
        return disabledTime;
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public String getExecutiveActions() {
        return executiveActions;
    }

    public String getCollabSite() {
        return collabSite;
    }

    public String getEpmPwaSite() {
        return epmPwaSite;
    }

    public String getDocumentationRepo() {
        return documentationRepo;
    }

    public String getDefectsReportSite() {
        return defectsReportSite;
    }

    public int getActiveRisks() {
        return activeRisks;
    }

    public Date getEpmLastSavedDate() {
        return epmLastSavedDate;
    }

    public Date getPwsLastUpdatedDate() {
        return pwsLastUpdatedDate;
    }

    public String getPwsLastUpdatedBy() {
        return pwsLastUpdatedBy;
    }

    public boolean isEpm() {
        return isEpm;
    }
}
