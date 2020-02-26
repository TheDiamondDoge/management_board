package com.aiksanov.api.project.web.DTO.summary;

public class ProjectDefaultDataDTO {
    private int projectId;
    private String projectName;

    public ProjectDefaultDataDTO() {
    }

    public ProjectDefaultDataDTO(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}