package com.aiksanov.api.project.data.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HealthIndicatorsCommentsPK implements Serializable {
    @Column(name = "project_id", insertable = false, updatable = false)
    private int projectID;

    @Column(name = "label")
    private String label;

    public HealthIndicatorsCommentsPK() {
    }

    public HealthIndicatorsCommentsPK(int projectID, String label) {
        this.projectID = projectID;
        this.label = label;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
        HealthIndicatorsCommentsPK that = (HealthIndicatorsCommentsPK) o;
        return projectID == that.projectID &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, label);
    }
}
