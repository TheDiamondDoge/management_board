package com.aiksanov.api.project.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "prj_status_report_snapshots")
public class StatusReportSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] pptSnapshot;

    public StatusReportSnapshot() {
    }

    public StatusReportSnapshot(Integer id, byte[] pptSnapshot) {
        this.id = id;
        this.pptSnapshot = pptSnapshot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPptSnapshot() {
        return pptSnapshot;
    }

    public void setPptSnapshot(byte[] pptSnapshot) {
        this.pptSnapshot = pptSnapshot;
    }
}
