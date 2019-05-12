package com.aiksanov.api.project.data.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prj_milestones")
public class Milestone {
    @EmbeddedId
    MilestonePK milestonePK;

    @Column(name = "baseline_date")
    private Date baselineDate;

    @Column(name = "actual_date")
    private Date actualDate;

    @Column(name = "completion")
    private int completion;

    @Column(name = "url")
    private String url;

    @Column(name = "show_in_timeline")
    private boolean isShownInTimeline;

    public Milestone() {
    }

    public Milestone(MilestonePK milestonePK, Date baselineDate, Date actualDate, int completion, String url, boolean isShownInTimeline) {
        this.milestonePK = milestonePK;
        this.baselineDate = baselineDate;
        this.actualDate = actualDate;
        this.completion = completion;
        this.url = url;
        this.isShownInTimeline = isShownInTimeline;
    }

    public MilestonePK getMilestonePK() {
        return milestonePK;
    }

    public void setMilestonePK(MilestonePK milestonePK) {
        this.milestonePK = milestonePK;
    }

    public Date getBaselineDate() {
        return baselineDate;
    }

    public void setBaselineDate(Date baselineDate) {
        this.baselineDate = baselineDate;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isShownInTimeline() {
        return isShownInTimeline;
    }

    public void setShownInTimeline(boolean shownInTimeline) {
        isShownInTimeline = shownInTimeline;
    }
}

