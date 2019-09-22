package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class QualityIndicatorsPK implements Serializable {
    private int projectID;
    private String kpiID;
    private int rowNumber;

    public QualityIndicatorsPK() {
    }

    public QualityIndicatorsPK(int projectID, String kpiID, int rowNumber) {
        this.projectID = projectID;
        this.kpiID = kpiID;
        this.rowNumber = rowNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityIndicatorsPK that = (QualityIndicatorsPK) o;
        return projectID == that.projectID &&
                rowNumber == that.rowNumber &&
                kpiID.equals(that.kpiID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, kpiID, rowNumber);
    }
}
