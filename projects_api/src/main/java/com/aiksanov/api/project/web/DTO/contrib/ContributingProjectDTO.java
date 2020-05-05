package com.aiksanov.api.project.web.DTO.contrib;

import com.aiksanov.api.project.web.DTO.MilestoneDTO;

import java.util.List;

public class ContributingProjectDTO {
    private int projectId;
    private String projectName;
    private String projectState;
    private MilestoneDTO lastApproved;
    private List<MilestoneDTO> milestones;

    public ContributingProjectDTO() {
    }

    public ContributingProjectDTO(int projectId, String projectName, String projectState, MilestoneDTO lastApproved,
                                  List<MilestoneDTO> milestones) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectState = projectState;
        this.lastApproved = lastApproved;
        this.milestones = milestones;
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

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public MilestoneDTO getLastApproved() {
        return lastApproved;
    }

    public void setLastApproved(MilestoneDTO lastApproved) {
        this.lastApproved = lastApproved;
    }

    public List<MilestoneDTO> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<MilestoneDTO> milestones) {
        this.milestones = milestones;
    }
}