package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.StatusReportPK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prj_status_report")
public class StatusReport {
    @EmbeddedId
    private StatusReportPK pk;

    @Column(name = "executive_summary")
    private String executiveSummary;

    @Column(name = "red_flag")
    private String redFlag;

    @Column(name = "orange_flag")
    private String orangeFlag;

    @Column(name = "green_flag")
    private String greenFlag;

    @Column(name = "details")
    private String details;

    @Column(name = "timestamp")
    private Date timestamp;

    public StatusReport() {

    }

    public StatusReportPK getPk() {
        return pk;
    }

    public void setPk(StatusReportPK pk) {
        this.pk = pk;
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public String getRedFlag() {
        return redFlag;
    }

    public void setRedFlag(String redFlag) {
        this.redFlag = redFlag;
    }

    public String getOrangeFlag() {
        return orangeFlag;
    }

    public void setOrangeFlag(String orangeFlag) {
        this.orangeFlag = orangeFlag;
    }

    public String getGreenFlag() {
        return greenFlag;
    }

    public void setGreenFlag(String greenFlag) {
        this.greenFlag = greenFlag;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}