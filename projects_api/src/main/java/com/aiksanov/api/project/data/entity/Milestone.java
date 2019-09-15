package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.MilestonePK;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prj_milestones")
public class Milestone {
    @EmbeddedId
    private MilestonePK milestonePK;

    @Column(name = "baseline_date")
    private Date baselineDate;

    @Column(name = "actual_date")
    private Date actualDate;

    @Column(name = "completion")
    private int completion;

    @Column(name = "url")
    private String meetingMinutes;

    @Column(name = "show_in_timeline")
    private boolean isShown;

    public Milestone() {
    }

    public Milestone(MilestonePK milestonePK, Date baselineDate, Date actualDate, int completion, String meetingMinutes, boolean isShown) {
        this.milestonePK = milestonePK;
        this.baselineDate = baselineDate;
        this.actualDate = actualDate;
        this.completion = completion;
        this.meetingMinutes = meetingMinutes;
        this.isShown = isShown;
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

    public String getMeetingMinutes() {
        return meetingMinutes;
    }

    public void setMeetingMinutes(String meetingMinutes) {
        this.meetingMinutes = meetingMinutes;
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }
}

