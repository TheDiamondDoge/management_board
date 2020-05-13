package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum WorkspaceStatus {
    ENABLED("Enabled"),
    DISABLED("Disabled");
    private String value;

    WorkspaceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WorkspaceStatus getTypeIgnoreCase(String type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException();
        return WorkspaceStatus.valueOf(type.toUpperCase());
    }
}
