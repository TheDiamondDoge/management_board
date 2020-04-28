package com.aiksanov.api.project.data.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatusReportPK implements Serializable {
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "report_id")
    private int reportId;

    public StatusReportPK() {
    }

    public StatusReportPK(Integer projectId, int reportId) {
        this.projectId = projectId;
        this.reportId = reportId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusReportPK that = (StatusReportPK) o;
        return reportId == that.reportId &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, reportId);
    }
}
