package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectTypes;
import lombok.Getter;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
public class PWSTableViewDTO {
    private int projectId;
    private int overallProjectHealth;
    private String projectName;
    private String projectRelease;
    private String projectManager;
    private String businessLineManager;
    private String productLineManager;
    private String businessDivision;
    private String businessUnit;
    private String productLine;
    private ProjectStates projectState;
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

    public PWSTableViewDTO(Project projectInfo, HealthIndicators indicators, List<Milestone> milestones) {
        if (Objects.nonNull(projectInfo)) {
            projectInfoMapping(projectInfo);
        }

        if (Objects.nonNull(milestones)) {
            milestonesMapping(milestones);
        }

        if (Objects.nonNull(indicators)) {
            indicatorsMapping(indicators);
        }
    }

    private void projectInfoMapping(Project projectInfo) {
        this.projectId = projectInfo.getProjectID();
        this.projectName = projectInfo.getName();
        this.projectManager = projectInfo.getManager();
        this.projectState = projectInfo.getState();
        this.projectRigor = projectInfo.getRigor().getLabel();
        this.projectType = projectInfo.getType();

        Product product = projectInfo.getProduct();
        if (Objects.nonNull(product)) {
            productMapping(product);
        }

        ProjectAdditionalInfo additionalInfo = projectInfo.getAdditionalInfo();
        if (Objects.nonNull(additionalInfo)) {
            projectAdditionalInfoMapping(additionalInfo);
        }
    }

    private void productMapping(Product product) {
        this.productLineManager = product.getManager();
        this.projectRelease = product.getRelease();
        this.businessDivision = product.getDivision();
        this.businessUnit = product.getBusinessUnit();
        this.productLine = product.getProductLine();
    }

    private void projectAdditionalInfoMapping(ProjectAdditionalInfo additionalInfo) {
        this.businessLineManager = additionalInfo.getBusinessLineManager();
    }

    private void milestonesMapping(List<Milestone> milestones) {
        this.orDate = getMilestoneBaselineDate(milestones, "OR");
        this.dr0date = getMilestoneBaselineDate(milestones, "DR0");
        this.dr1date = getMilestoneBaselineDate(milestones, "DR1");
        this.dr2date = getMilestoneBaselineDate(milestones, "DR2");
        this.obrDate = getMilestoneBaselineDate(milestones, "OBR");
        this.ciDate = getMilestoneBaselineDate(milestones, "CI");
        this.dr3date = getMilestoneBaselineDate(milestones, "DR3");
        this.trDate = getMilestoneBaselineDate(milestones, "TR");
        this.dr4date = getMilestoneBaselineDate(milestones, "DR4");
        this.dr5date = getMilestoneBaselineDate(milestones, "DR5");
    }

    private void indicatorsMapping(HealthIndicators indicators) {
        this.overallProjectHealth = indicators.getOverall();
        this.scheduleStatus = indicators.getSchedule();
        this.contentStatus = indicators.getScope();
        this.qualityStatus = indicators.getQuality();
        this.costStatus = indicators.getCost();
    }

    private Date getMilestoneBaselineDate(List<Milestone> milestoneList, String milestoneLabel) {
        Optional<Milestone> milestone = milestoneList.stream()
                .filter(m -> m.getMilestonePK().getLabel().toUpperCase().equals(milestoneLabel.toUpperCase()))
                .findAny();

        return milestone.map(Milestone::getActualDate).orElse(null);
    }
}
