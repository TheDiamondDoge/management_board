package com.aiksanov.api.project.web.DTO;

public class ContributingDTO {
    private int projectID;
    private String projectName;

    public ContributingDTO() {
    }

    public ContributingDTO(int projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    public int getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }
}
