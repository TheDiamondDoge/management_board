package com.aiksanov.api.project.web.DTO.summary;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.util.enums.ProjectTypes;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;

import java.util.Date;
import java.util.Objects;

public class ProjectDefaultDataDTO {
    private int projectId;
    private String projectName;
    private String projectType;
    private String projectDivision;
    private String metricsScope;
    private String requirementsUrl;
    private WorkspaceStatus workspaceStatus;
    private Date dr1Actual;
    private boolean epm;
    private boolean maintenance;

    public ProjectDefaultDataDTO() {
    }

    public ProjectDefaultDataDTO(Project project, Milestone dr1, String metricsScope, String requirementsUrl) {
        this.projectId = project.getProjectID();
        this.projectName = project.getName();
        this.metricsScope = metricsScope;
        this.requirementsUrl = requirementsUrl;
        this.epm = project.isEpm();
        this.setProjectType(project.getType());

        if (Objects.nonNull(project.getProduct())) {
            this.projectDivision = project.getProduct().getDivision();
        }

        if (Objects.nonNull(project.getWorkspaceInfo())) {
            this.setWorkspaceStatus(project.getWorkspaceInfo().getStatus());
        }

        if (Objects.nonNull(dr1)) {
            this.dr1Actual = dr1.getActualDate();
        }

        if (Objects.nonNull(project.getAdditionalInfo())) {
            this.maintenance = project.getAdditionalInfo().isMaintenance();
        }
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

    public String getProjectType() {
        return this.projectType;
    }

    public void setProjectType(ProjectTypes projectType) {
        if (Objects.isNull(projectType)) {
            this.projectType = null;
        }
        this.projectType = projectType.getValue();
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectDivision() {
        return projectDivision;
    }

    public void setProjectDivision(String projectDivision) {
        this.projectDivision = projectDivision;
    }

    public String getMetricsScope() {
        return metricsScope;
    }

    public void setMetricsScope(String metricsScope) {
        this.metricsScope = metricsScope;
    }

    public String getRequirementsUrl() {
        return requirementsUrl;
    }

    public void setRequirementsUrl(String requirementsUrl) {
        this.requirementsUrl = requirementsUrl;
    }

    public WorkspaceStatus getWorkspaceStatus() {
        return workspaceStatus;
    }

    public void setWorkspaceStatus(WorkspaceStatus workspaceStatus) {
        if (Objects.nonNull(workspaceStatus)) {
            this.workspaceStatus = workspaceStatus;
        }
    }

    public Date getDr1Actual() {
        return dr1Actual;
    }

    public void setDr1Actual(Date dr1Actual) {
        this.dr1Actual = dr1Actual;
    }

    public boolean isEpm() {
        return epm;
    }

    public void setEpm(boolean epm) {
        this.epm = epm;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }
}