package com.aiksanov.api.project.util.enums;

public enum WorkspaceStatus {
    ENABLED("ENABLED"),
    DISABLED("DISABLED");
    private String value;

    WorkspaceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
