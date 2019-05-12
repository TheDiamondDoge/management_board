package com.aiksanov.api.project.data.domain;

public enum HealthStatus {
    OVERALL("overall"), SCHEDULE("schedule"), SCOPE("scope"), QUALITY("quality"), COST("cost");
    private String label;

    private HealthStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
