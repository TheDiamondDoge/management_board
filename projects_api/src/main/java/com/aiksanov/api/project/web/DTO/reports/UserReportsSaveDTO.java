package com.aiksanov.api.project.web.DTO.reports;

public class UserReportsSaveDTO {
    private String type;
    private String data;

    public UserReportsSaveDTO() {
    }

    public UserReportsSaveDTO(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
