package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.MilestonePK;

import java.sql.Date;

public class MilestoneDTO {
    private int projectID;
    private String label;
    private Date baselineDate;
    private Date actualDate;
    private int completion;
    private String url;
    private boolean isShownInTimeline;

    public MilestoneDTO() {
    }

    public MilestoneDTO(Milestone milestone) {
        this.projectID = milestone.getMilestonePK().getProjectID();
        this.label = milestone.getMilestonePK().getLabel();
        this.baselineDate = milestone.getBaselineDate();
        this.actualDate = milestone.getActualDate();
        this.completion = milestone.getCompletion();
        this.url = milestone.getUrl();
        this.isShownInTimeline = milestone.isShownInTimeline();
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

    public String getUrl() {
        return url;
    }

    public boolean isShownInTimeline() {
        return isShownInTimeline;
    }
}
