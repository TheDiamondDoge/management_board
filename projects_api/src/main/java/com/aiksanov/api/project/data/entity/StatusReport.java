package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_status_report")
public class StatusReport {
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "executive_summary")
    private String executiveSummary;

    @Column(name = "actions_needed")
    private String actionsNeeded;

    public StatusReport() {
    }

    public StatusReport(Integer projectId, String executiveSummary, String actionsNeeded) {
        this.projectId = projectId;
        this.executiveSummary = executiveSummary;
        this.actionsNeeded = actionsNeeded;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public String getActionsNeeded() {
        return actionsNeeded;
    }

    public void setActionsNeeded(String actionsNeeded) {
        this.actionsNeeded = actionsNeeded;
    }
}
