package com.aiksanov.api.project.web.DTO.reports;

public class UserReportsSaveDTO {
    private String summary;
    private String red;
    private String orange;
    private String green;
    private String details;

    public UserReportsSaveDTO() {
    }

    public UserReportsSaveDTO(String summary, String red, String orange, String green, String details) {
        this.summary = summary;
        this.red = red;
        this.orange = orange;
        this.green = green;
        this.details = details;
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
