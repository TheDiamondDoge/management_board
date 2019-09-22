package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;

import javax.persistence.*;

@Entity
@Table(name = "prj_indicators_quality")
@IdClass(QualityIndicatorsPK.class)
public class QualityIndicators {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Id
    @Column(name = "kpi_id")
    private String kpiID;

    @Id
    @Column(name = "row_num")
    private int rowNumber;

    @Column(name = "objective")
    private int objective;

    @Column(name = "actual")
    private int actual;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "project_id", referencedColumnName = "project_id", insertable = false, updatable = false),
            @JoinColumn(name = "kpi_id", referencedColumnName = "kpi_id", insertable = false, updatable = false)
    })
    private QualityIndicatorsComments comment;

    public QualityIndicators() {
    }

    public QualityIndicators(int projectID, String kpiID, int rowNumber, int objective, int actual, QualityIndicatorsComments comment) {
        this.projectID = projectID;
        this.kpiID = kpiID;
        this.rowNumber = rowNumber;
        this.objective = objective;
        this.actual = actual;
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

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getObjective() {
        return objective;
    }

    public void setObjective(int objective) {
        this.objective = objective;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public QualityIndicatorsComments getComment() {
        return comment;
    }

    public void setComment(QualityIndicatorsComments comment) {
        this.comment = comment;
    }
}
