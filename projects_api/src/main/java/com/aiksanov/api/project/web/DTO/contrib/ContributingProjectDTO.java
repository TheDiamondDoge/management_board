package com.aiksanov.api.project.web.DTO.contrib;

import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;

import java.util.List;

public class ContributingProjectDTO {
    private String projectName;
    private String projectState;
    private MilestoneDTO lastApproved;
    private List<MilestoneDTO> milestones;

    public ContributingProjectDTO() {
    }

    public ContributingProjectDTO(String projectName, String projectState, MilestoneDTO lastApproved,
                                  List<MilestoneDTO> milestones) {
        this.projectName = projectName;
        this.projectState = projectState;
        this.lastApproved = lastApproved;
        this.milestones = milestones;
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