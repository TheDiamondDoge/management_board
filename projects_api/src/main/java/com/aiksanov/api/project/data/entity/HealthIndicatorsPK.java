package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class HealthIndicatorsPK implements Serializable{
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "label")
    private String label;

    @Column(name = "modification_date")
    private Date modificationDate;

    public HealthIndicatorsPK() {
    }

    public HealthIndicatorsPK(int projectID, String label, Date modificationDate) {
        this.projectID = projectID;
        this.label = label;
        this.modificationDate = modificationDate;
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

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthIndicatorsPK that = (HealthIndicatorsPK) o;
        return projectID == that.projectID &&
                Objects.equals(label, that.label) &&
                Objects.equals(modificationDate, that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, label, modificationDate);
    }
}
