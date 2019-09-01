package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.Milestone;

import java.sql.Date;

public class MilestoneDTO {
    private int projectID;
    private String label;
    private Date baselineDate;
    private Date actualDate;
    private int completion;
    private String meetingMinutes;
    private boolean isShown;

    public MilestoneDTO() {
    }

    public MilestoneDTO(Milestone milestone) {
        this.projectID = milestone.getMilestonePK().getProjectID();
        this.label = milestone.getMilestonePK().getLabel();
        this.baselineDate = milestone.getBaselineDate();
        this.actualDate = milestone.getActualDate();
        this.completion = milestone.getCompletion();
        this.meetingMinutes = milestone.getMeetingMinutes();
        this.isShown = milestone.isShown();
    }

    public int getProjectID() {
        return projectID;
    }

    public String getLabel() {
        return label;
    }

    public Date getBaselineDate() {
        return baselineDate;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public int getCompletion() {
        return completion;
    }

    public String getMeetingMinutes() {
        return meetingMinutes;
    }

    public boolean isShown() {
        return isShown;
    }
}
