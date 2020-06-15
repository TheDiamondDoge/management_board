package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum ProjectTemplates {
    PMO("PMO"),
    OFFER("Offer"),
    PMO_COMPOSITE("PMO Composite"),
    NA("N/A"),
    RD_COMPONENT("RD Component"),
    RD("RD"),
    RD_SUPPORT("RD Support"),
    RD_SOLUTION("RD Solution"),
    OTHER("Other");
    private final String label;

    ProjectTemplates(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static ProjectTemplates getTypeIgnoreCase(String type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException();
        return ProjectTemplates.valueOf(type.toUpperCase());
    }
}
