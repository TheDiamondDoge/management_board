package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MilestonePK implements Serializable {
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "milestone_label")
    private String label;

    public MilestonePK() {
    }

    public MilestonePK(int projectID, String label) {
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
        MilestonePK that = (MilestonePK) o;
        return projectID == that.projectID &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, label);
    }
}
