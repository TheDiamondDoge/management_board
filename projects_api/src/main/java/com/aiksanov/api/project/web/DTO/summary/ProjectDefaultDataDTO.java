package com.aiksanov.api.project.web.DTO.summary;

import com.aiksanov.api.project.util.enums.ProjectTypes;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;

import java.util.Objects;

public class ProjectDefaultDataDTO {
    private int projectId;
    private String projectName;
    private String projectType;
    private String workspaceStatus;
    private boolean epm;
    private boolean composite;

    public ProjectDefaultDataDTO() {
    }

    public ProjectDefaultDataDTO(int projectId, String projectName, ProjectTypes projectType,
                                 WorkspaceStatus workspaceStatus, boolean epm, boolean composite) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.setProjectType(projectType);
        this.setWorkspaceStatus(workspaceStatus);
        this.epm = epm;
        this.composite = composite;
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

    public ProjectTypes getProjectType() {
        return ProjectTypes.getTypeIgnoreCase(projectType);
    }

    public void setProjectType(ProjectTypes projectType) {
        if (Objects.isNull(projectType)) {
            this.projectType = null;
        }
        this.projectType = projectType.getValue();
    }

    public String getWorkspaceStatus() {
        return workspaceStatus;
    }

    public void setWorkspaceStatus(WorkspaceStatus workspaceStatus) {
        if (Objects.nonNull(workspaceStatus)) {
            this.workspaceStatus = workspaceStatus.getValue();
        }
    }

    public boolean isEpm() {
        return epm;
    }

    public void setEpm(boolean epm) {
        this.epm = epm;
    }

    public boolean isComposite() {
        return composite;
    }

    public void setComposite(boolean composite) {
        this.composite = composite;
    }
}