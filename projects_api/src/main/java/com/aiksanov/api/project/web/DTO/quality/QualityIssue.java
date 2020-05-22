package com.aiksanov.api.project.web.DTO.quality;


import com.aiksanov.api.project.web.DTO.IndicatorsKpiAbstract;

public class QualityIssue extends IndicatorsKpiAbstract {
    private int id;
    private int projectId;
    private String crdbId;
    private String type;
    private String TSsubmitDate;

    public QualityIssue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getCrdbId() {
        return crdbId;
    }

    public void setCrdbId(String crdbId) {
        this.crdbId = crdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTSsubmitDate() {
        return TSsubmitDate;
    }

    public void setTSsubmitDate(String TSsubmitDate) {
        this.TSsubmitDate = TSsubmitDate;
    }
}
