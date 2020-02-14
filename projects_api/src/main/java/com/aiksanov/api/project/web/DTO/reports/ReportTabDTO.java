package com.aiksanov.api.project.web.DTO.reports;

import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsMinimalDTO;
import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksMinimalDTO;

import java.util.Date;
import java.util.List;

public class ReportTabDTO {
    private Date updatedOn;
    private String projectName;
    private String projectManager;
    private List<MilestoneDTO> milestones;
    private HealthIndicatorsMinimalDTO indicators;

    private List<RisksMinimalDTO> risks;

    public ReportTabDTO() {
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public List<MilestoneDTO> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<MilestoneDTO> milestones) {
        this.milestones = milestones;
    }

    public HealthIndicatorsMinimalDTO getIndicators() {
        return indicators;
    }

    public void setIndicators(HealthIndicatorsMinimalDTO indicators) {
        this.indicators = indicators;
    }

    public List<RisksMinimalDTO> getRisks() {
        return risks;
    }

    public void setRisks(List<RisksMinimalDTO> risks) {
        this.risks = risks;
    }
}
