package com.aiksanov.api.project.web.DTO.backlog;


import com.aiksanov.api.project.web.DTO.IndicatorsKpiAbstract;

public class BacklogIssue extends IndicatorsKpiAbstract {
    private int id;
    private int projectId;
    private String crdbId;
    private String inBacklogAtDR4;
    private String backlogReduction;

    public BacklogIssue() {
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

    public String getInBacklogAtDR4() {
        return inBacklogAtDR4;
    }

    public void setInBacklogAtDR4(String inBacklogAtDR4) {
        this.inBacklogAtDR4 = inBacklogAtDR4;
    }

    public String getBacklogReduction() {
        return backlogReduction;
    }

    public void setBacklogReduction(String backlogReduction) {
        this.backlogReduction = backlogReduction;
    }
}