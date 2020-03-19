package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "prj_cost_details")
public class CostDetails {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "updated")
    private Date updated;

    public CostDetails() {
    }

    public CostDetails(int projectId, Date date) {
        this.projectId = projectId;
        this.updated = date;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
