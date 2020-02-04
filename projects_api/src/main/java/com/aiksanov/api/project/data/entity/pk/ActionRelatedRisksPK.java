package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class ActionRelatedRisksPK implements Serializable {
    private int actionId;
    private int risksId;
    private int projectId;

    public ActionRelatedRisksPK() {
    }

    public ActionRelatedRisksPK(int actionId, int risksId, int projectId) {
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

    public int getRisksId() {
        return risksId;
    }

    public void setRisksId(int risksId) {
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
                risksId == that.risksId &&
                projectId == that.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionId, risksId, projectId);
    }
}
