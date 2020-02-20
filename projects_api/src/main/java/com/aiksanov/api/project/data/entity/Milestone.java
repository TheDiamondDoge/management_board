package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.MilestonePK;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "prj_milestones")
public class Milestone implements Comparable<Milestone> {
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
    private boolean shown;

    public Milestone() {
    }

    public Milestone(MilestonePK milestonePK, Date baselineDate, Date actualDate, int completion, String meetingMinutes, boolean shown) {
        this.milestonePK = milestonePK;
        this.baselineDate = baselineDate;
        this.actualDate = actualDate;
        this.completion = completion;
        this.meetingMinutes = meetingMinutes;
        this.shown = shown;
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
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    @Override
    public int compareTo(Milestone o) {
        if (Objects.isNull(this.getActualDate()) || Objects.isNull(o.getActualDate())) {
            return 0;
        }
        return this.getActualDate().compareTo(o.getActualDate());
    }
}

