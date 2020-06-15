package com.aiksanov.api.project.util.enums;

public enum MilestoneLabels {
    OR ("OR"),
    DR1 ("DR1"),
    DR4 ("DR4");

    private final String label;

    MilestoneLabels(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
