package com.aiksanov.api.project.web.DTO.reports;

import com.aiksanov.api.project.data.entity.StatusReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportsDTO {
    private String summary;
    private String red;
    private String orange;
    private String green;
    private String details;

    public UserReportsDTO(StatusReport report) {
        this.setReports(report);
    }

    private void setReports(StatusReport report) {
        this.summary = report.getExecutiveSummary();
        this.red = report.getRedFlag();
        this.orange = report.getOrangeFlag();
        this.green = report.getGreenFlag();
        this.details = report.getDetails();
    }
}