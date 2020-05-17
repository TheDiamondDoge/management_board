package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum ProjectRigors {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    NONE("");
    private final String label;

    ProjectRigors(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static ProjectRigors getTypeIgnoreCase(String type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException();
        return ProjectRigors.valueOf(type.toUpperCase());
    }
}
