package com.aiksanov.api.project.util.enums;

public enum HealthStatus {
    OVERALL("overall"),
    SCHEDULE("schedule"),
    SCOPE("scope"),
    QUALITY("quality"),
    COST("cost");
    private String label;

    HealthStatus(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}