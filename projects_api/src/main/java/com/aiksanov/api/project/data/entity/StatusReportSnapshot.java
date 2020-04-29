package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.StatusReportPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "prj_status_report_snapshots")
public class StatusReportSnapshot {
    @EmbeddedId
    private StatusReportPK pk;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "snapshot_json")
    private String shapshotJson;

    public StatusReportSnapshot(StatusReportPK pk, Date timestamp, String shapshotJson) {
        this.pk = pk;
        this.timestamp = timestamp;
        this.shapshotJson = shapshotJson;
    }

    public StatusReportSnapshot() {
    }

    public StatusReportPK getPk() {
        return pk;
    }

    public void setPk(StatusReportPK pk) {
        this.pk = pk;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getShapshotJson() {
        return shapshotJson;
    }

    public void setShapshotJson(String shapshotJson) {
        this.shapshotJson = shapshotJson;
    }
}
