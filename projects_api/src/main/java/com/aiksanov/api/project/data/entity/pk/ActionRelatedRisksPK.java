package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class ActionRelatedRisksPK implements Serializable {
    private int actionId;
    private String risksId;
    private int projectId;

    public ActionRelatedRisksPK() {
    }

    public ActionRelatedRisksPK(int actionId, String risksId, int projectId) {
        this.actionId = actionId;
        this.risksId = risksId;
        this.projectId = projectId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getRisksId() {
        return risksId;
    }

    public void setRisksId(String risksId) {
        this.risksId = risksId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionRelatedRisksPK that = (ActionRelatedRisksPK) o;
        return actionId == that.actionId &&
                projectId == that.projectId &&
                Objects.equals(risksId, that.risksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionId, risksId, projectId);
    }
}
