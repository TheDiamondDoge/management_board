package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.util.enums.ProjectTypes;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PWSTableViewDTO{
    private int overallProjectHealth;
    private String projectName;
    private String projectRelease;
    private String projectManager;
    private String businessLineManager;
    private String productLineManager;
    private String businessDivision;
    private String businessUnit;
    private String productLine;
    private String projectState;
    private String projectRigor;
    private ProjectTypes projectType;
    private Date orDate;
    private Date dr0date;
    private Date dr1date;
    private Date obrDate;
    private Date dr2date;
    private Date ciDate;
    private Date dr3date;
    private Date trDate;
    private Date dr4date;
    private Date dr5date;
    private int scheduleStatus;
    private int contentStatus;
    private int qualityStatus;
    private int costStatus;

    public PWSTableViewDTO(Project projectInfo) {
        if (Objects.nonNull(projectInfo)){
            projectInfoMapping(projectInfo);
        }
    }

    private void projectInfoMapping(Project projectInfo){
        this.projectName = projectInfo.getName();
        this.projectManager = projectInfo.getManager();
        this.projectState = projectInfo.getState();
        this.projectRigor = projectInfo.getRigor();
        this.projectType = projectInfo.getType();

        Product product = projectInfo.getProduct();
        if (Objects.nonNull(product)) {
            productMapping(product);
        }

        ProjectAdditionalInfo additionalInfo = projectInfo.getAdditionalInfo();
        if (Objects.nonNull(additionalInfo)){
            projectAdditionalInfoMapping(additionalInfo);
        }
    }

    private void productMapping(Product product){
        this.productLineManager = product.getManager();
        this.projectRelease = product.getRelease();
        this.businessDivision = product.getDivision();
        this.businessUnit = product.getBusinessUnit();
        this.productLine = product.getProductLine();
    }

    private void projectAdditionalInfoMapping(ProjectAdditionalInfo additionalInfo){
        this.businessLineManager = additionalInfo.getBusinessLineManager();
    }

    private void milestonesMapping(List<Milestone> milestones){
        this.orDate = getMilestoneBaselineDate(milestones, "OR");
        this.dr0date = getMilestoneBaselineDate(milestones, "DR0");
        this.dr1date = getMilestoneBaselineDate(milestones, "DR1");
        this.dr2date = getMilestoneBaselineDate(milestones, "DR2");
        this.ciDate = getMilestoneBaselineDate(milestones, "CI");
        this.dr3date = getMilestoneBaselineDate(milestones, "DR3");
        this.trDate = getMilestoneBaselineDate(milestones, "TR");
        this.dr4date = getMilestoneBaselineDate(milestones, "DR4");
        this.dr5date = getMilestoneBaselineDate(milestones, "DR5");
    }

    private Date getMilestoneBaselineDate(List<Milestone> milestoneList, String milestoneLabel){
        Optional<Milestone> milestone = milestoneList.stream()
                .filter(m -> m.getMilestonePK().getLabel().toUpperCase().equals(milestoneLabel.toUpperCase()))
                .findAny();

        return milestone.map(Milestone::getBaselineDate).orElse(null);
    }

    public int getOverallProjectHealth() {
        return overallProjectHealth;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectRelease() {
        return projectRelease;
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

    public String getBusinessDivision() {
        return businessDivision;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public String getProductLine() {
        return productLine;
    }

    public String getProjectState() {
        return projectState;
    }

    public String getProjectRigor() {
        return projectRigor;
    }

    public ProjectTypes getProjectType() {
        return projectType;
    }

    public Date getOrDate() {
        return orDate;
    }

    public Date getDr0date() {
        return dr0date;
    }

    public Date getDr1date() {
        return dr1date;
    }

    public Date getObrDate() {
        return obrDate;
    }

    public Date getDr2date() {
        return dr2date;
    }

    public Date getCiDate() {
        return ciDate;
    }

    public Date getDr3date() {
        return dr3date;
    }

    public Date getTrDate() {
        return trDate;
    }

    public Date getDr4date() {
        return dr4date;
    }

    public Date getDr5date() {
        return dr5date;
    }

    public int getScheduleStatus() {
        return scheduleStatus;
    }

    public int getContentStatus() {
        return contentStatus;
    }

    public int getQualityStatus() {
        return qualityStatus;
    }

    public int getCostStatus() {
        return costStatus;
    }
}
