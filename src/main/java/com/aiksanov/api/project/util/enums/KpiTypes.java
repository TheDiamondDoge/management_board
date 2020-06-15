package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum KpiTypes {
    BACKLOG("backlog"),
    QUALITY("quality"),
    DEFECTS("defects");
    private final String value;

    KpiTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static KpiTypes getTypeIgnoreCase(String type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException();
        return KpiTypes.valueOf(type.toUpperCase());
    }
}
