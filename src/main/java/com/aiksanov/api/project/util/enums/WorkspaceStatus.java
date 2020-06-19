package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum WorkspaceStatus {
    ENABLED, DISABLED;

    public static WorkspaceStatus getWorkspaceStatusIgnoreCase(String status) {
        for (WorkspaceStatus w : WorkspaceStatus.values()) {
            if (Objects.nonNull(status) && w.name().equalsIgnoreCase(status)) {
                return w;
            }
        }

        return null;
    }
}
