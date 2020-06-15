package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum ReportTypes {
    SUMMARY, RED, ORANGE, GREEN, DETAILS;

    public static ReportTypes getTypeIgnoreCase(String type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException();
        return ReportTypes.valueOf(type.toUpperCase());
    }
}
