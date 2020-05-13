package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum ProjectTypes {
    OFFER("Offer"),
    PRODUCT("Product"),
    OEM_PRODUCT("OEM Product"),
    RD_PRODUCT("R&D Product");
    private final String value;

    ProjectTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProjectTypes getTypeIgnoreCase(String type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException();
        return ProjectTypes.valueOf(type.toUpperCase());
    }
}
