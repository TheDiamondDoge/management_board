package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_jira_params")
public class JiraParams {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "metrics_scope")
    private String metricsScope;

    @Column(name ="rq_release")
    private String rqRelease;

    public JiraParams() {
    }

    public JiraParams(int projectID, String metricsScope, String rqRelease) {
        this.projectID = projectID;
        this.metricsScope = metricsScope;
        this.rqRelease = rqRelease;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getMetricsScope() {
        return metricsScope;
    }

    public void setMetricsScope(String metricsScope) {
        this.metricsScope = metricsScope;
    }

    public String getRqRelease() {
        return rqRelease;
    }

    public void setRqRelease(String rqRelease) {
        this.rqRelease = rqRelease;
    }
}
