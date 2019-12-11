package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class RiskPK implements Serializable {
    private int projectId;
    private float riskId;

    public RiskPK() {
    }

    public RiskPK(int projectId, float riskId) {
        this.projectId = projectId;
        this.riskId = riskId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public float getRiskId() {
        return riskId;
    }

    public void setRiskId(float riskId) {
        this.riskId = riskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskPK riskPK = (RiskPK) o;
        return projectId == riskPK.projectId &&
                Float.compare(riskPK.riskId, riskId) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, riskId);
    }
}
