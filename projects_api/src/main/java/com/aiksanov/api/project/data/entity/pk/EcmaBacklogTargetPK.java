package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class EcmaBacklogTargetPK implements Serializable {
    private int projectId;
    private String label;

    public EcmaBacklogTargetPK() {
    }

    public EcmaBacklogTargetPK(int projectId, String label) {
        this.projectId = projectId;
        this.label = label;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcmaBacklogTargetPK that = (EcmaBacklogTargetPK) o;
        return projectId == that.projectId &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, label);
    }
}
