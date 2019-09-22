package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class QualityIndicatorsCommentsPK implements Serializable {
    private int projectID;
    private String kpiID;

    public QualityIndicatorsCommentsPK() {
    }

    public QualityIndicatorsCommentsPK(int projectID, String kpiID) {
        this.projectID = projectID;
        this.kpiID = kpiID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityIndicatorsCommentsPK that = (QualityIndicatorsCommentsPK) o;
        return projectID == that.projectID &&
                kpiID.equals(that.kpiID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, kpiID);
    }
}
