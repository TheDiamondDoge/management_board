package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class CostPK implements Serializable {
    private int projectId;
    private int type;
    private int state;

    public CostPK() {
    }

    public CostPK(int projectId, int type, int state) {
        this.projectId = projectId;
        this.type = type;
        this.state = state;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostPK costPK = (CostPK) o;
        return projectId == costPK.projectId &&
                type == costPK.type &&
                state == costPK.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, type, state);
    }
}
