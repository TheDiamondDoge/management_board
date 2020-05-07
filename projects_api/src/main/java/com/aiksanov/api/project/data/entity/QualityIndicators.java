package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    private String objective;

    @Column(name = "actual")
    private String actual;


    public QualityIndicators() {
    }

    public QualityIndicators(int projectID, String kpiID, int rowNumber, String objective, String actual) {
        this.projectID = projectID;
        this.kpiID = kpiID;
        this.rowNumber = rowNumber;
        this.objective = objective;
        this.actual = actual;
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}
