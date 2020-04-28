package com.aiksanov.api.project.web.DTO.reports;

import java.util.Date;

public class ReportTabDTO {
    private Date updatedOn;
    private String projectName;
    private String projectManager;

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

}
