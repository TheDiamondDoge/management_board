package com.aiksanov.api.project.web.DTO.reports;

import com.aiksanov.api.project.data.entity.StatusReport;

public class UserReportsDTO {
    private String summary;
    private String red;
    private String orange;
    private String green;
    private String details;

    public UserReportsDTO() {
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getOrange() {
        return orange;
    }

    public void setOrange(String orange) {
        this.orange = orange;
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}