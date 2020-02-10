package com.aiksanov.api.project.web.DTO;

import java.util.List;

public class RequirementsDTO {
    private String reqId;
    private String headline;
    private String priority;
    private String status;
    private List<String> fixVersions;
    private String components;

    public RequirementsDTO() {
    }

    public RequirementsDTO(String reqId, String headline, String priority, String status, List<String> fixVersions, String components) {
        this.reqId = reqId;
        this.headline = headline;
        this.priority = priority;
        this.status = status;
        this.fixVersions = fixVersions;
        this.components = components;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(List<String> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }
}