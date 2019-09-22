package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsCommentsPK;

import javax.persistence.*;

@Entity
@Table(name = "prj_indicators_quality_comments")
@IdClass(QualityIndicatorsCommentsPK.class)
public class QualityIndicatorsComments {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Id
    @Column(name = "kpi_id")
    private String kpiID;

    @Column(name = "comment")
    private String comment;

    public QualityIndicatorsComments() {
    }

    public QualityIndicatorsComments(int projectID, String kpiID, String comment) {
        this.projectID = projectID;
        this.kpiID = kpiID;
        this.comment = comment;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getKpiID() {
        return kpiID;
    }

    public void setKpiID(String kpiID) {
        this.kpiID = kpiID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
