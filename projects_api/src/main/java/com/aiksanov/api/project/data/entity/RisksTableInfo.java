package com.aiksanov.api.project.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "prj_risks_info")
public class RisksTableInfo {
    @Id
    private int projectId;
    private Date uploadedOn;
    private String uploadedBy;

    public RisksTableInfo() {
    }

    public RisksTableInfo(int projectId, Date uploadedOn, String uploadedBy) {
        this.projectId = projectId;
        this.uploadedOn = uploadedOn;
        this.uploadedBy = uploadedBy;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
