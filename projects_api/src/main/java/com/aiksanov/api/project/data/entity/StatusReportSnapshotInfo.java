package com.aiksanov.api.project.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "prj_status_report_snapshots_info")
public class StatusReportSnapshotInfo {
    @Id
    private int id;
    private int projectId;
    private Date createdOn;
    private String createdBy;

    public StatusReportSnapshotInfo() {
    }

    public StatusReportSnapshotInfo(int id, int projectId, Date createdOn, String createdBy) {
        this.id = id;
        this.projectId = projectId;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
