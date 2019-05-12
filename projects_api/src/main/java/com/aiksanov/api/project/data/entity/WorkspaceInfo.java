package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "prj_workspace_info")
public class WorkspaceInfo {
    @Id
    @Column(name = "project_id")
    private int id;

    @Column(name = "workspace_status")
    private String status;

    @Column(name = "workspace_created")
    private Date created;

    @Column(name = "workspace_modified")
    private Date modified;

    @Column(name = "workspace_modified_by")
    private String modifiedBy;

    public WorkspaceInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
