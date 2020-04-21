package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.Milestone;

import java.sql.Date;

public class MilestoneDTO {
    private String label;
    private Date baselineDate;
    private Date actualDate;
    private int completion;
    private String meetingMinutes;
    private boolean shown;

    public MilestoneDTO() {
    }

    public MilestoneDTO(Milestone milestone) {
        this.label = milestone.getMilestonePK().getLabel();
        this.baselineDate = milestone.getBaselineDate();
        this.actualDate = milestone.getActualDate();
        this.completion = milestone.getCompletion();
        this.meetingMinutes = milestone.getMeetingMinutes();
        this.shown = milestone.isShown();
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
        return shown;
    }
}
