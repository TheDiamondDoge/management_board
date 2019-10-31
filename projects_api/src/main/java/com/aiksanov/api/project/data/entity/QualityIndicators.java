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
    private int objective;

    @Column(name = "actual")
    private int actual;

    //TODO learn how to map properly (deletion error)
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @MapsId("kpi_id")
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumns({
//            @JoinColumn(name = "project_id", referencedColumnName = "project_id"),
//            @JoinColumn(name = "kpi_id", referencedColumnName = "kpi_id")
//    })
//    private QualityIndicatorsComments comment;

    public QualityIndicators() {
    }

    public QualityIndicators(int projectID, String kpiID, int rowNumber, int objective, int actual) {
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
}
