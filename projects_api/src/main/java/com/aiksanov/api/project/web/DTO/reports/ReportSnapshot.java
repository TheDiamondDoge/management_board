package com.aiksanov.api.project.web.DTO.reports;

import java.util.Date;
import java.util.Objects;

public class ReportSnapshot implements Comparable<ReportSnapshot> {
    private int reportId;
    private Date timestamp;

    public ReportSnapshot(int reportId, Date timestamp) {
        this.reportId = reportId;
        this.timestamp = timestamp;
    }

    public ReportSnapshot() {
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(ReportSnapshot o) {
        if (Objects.isNull(this.getTimestamp())) return -1;
        if (Objects.isNull(o.getTimestamp())) return 1;
        return o.getTimestamp().compareTo(this.getTimestamp());
    }
}
