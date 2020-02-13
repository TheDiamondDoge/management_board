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

    @Column(name = "red_flag")
    private String redFlag;

    @Column(name = "orange_flag")
    private String orangeFlag;

    @Column(name = "green_flag")
    private String greenFlag;

    @Column(name = "details")
    private String details;

    public StatusReport() {

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

    public String getRedFlag() {
        return redFlag;
    }

    public void setRedFlag(String redFlag) {
        this.redFlag = redFlag;
    }

    public String getOrangeFlag() {
        return orangeFlag;
    }

    public void setOrangeFlag(String orangeFlag) {
        this.orangeFlag = orangeFlag;
    }

    public String getGreenFlag() {
        return greenFlag;
    }

    public void setGreenFlag(String greenFlag) {
        this.greenFlag = greenFlag;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}