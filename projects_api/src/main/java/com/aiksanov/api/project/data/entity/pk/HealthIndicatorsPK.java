package com.aiksanov.api.project.data.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Embeddable
public class HealthIndicatorsPK implements Serializable{
    @Column(name = "project_id", insertable = false, updatable = false)
    private int projectID;

    @Column(name = "modification_date")
    private Date modificationDate;

    public HealthIndicatorsPK() {
    }

    public HealthIndicatorsPK(int projectID, Date modificationDate) {
        this.projectID = projectID;
        this.modificationDate = modificationDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
                Objects.equals(modificationDate, that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, modificationDate);
    }
}
