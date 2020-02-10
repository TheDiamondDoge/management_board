package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.CostPK;

import javax.persistence.*;

@Entity
@Table(name = "prj_cost")
@IdClass(CostPK.class)
public class Cost {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "type")
    private int type;

    @Id
    @Column(name = "state")
    private int state;

    @Column(name = "milestone")
    private String milestone;

    @Column(name = "value")
    private double value;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id", insertable = false, updatable = false)
    private CostDetails updated;

    public Cost() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CostDetails getUpdated() {
        return updated;
    }

    public void setUpdated(CostDetails updated) {
        this.updated = updated;
    }
}
