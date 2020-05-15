package com.aiksanov.api.project.util.enums;

import java.util.Objects;

public enum ProjectTypes {
    OFFER("Offer"),
    PRODUCT("Product"),
    OEM_PRODUCT("OEM Product"),
    OFFER_PRODUCT("Offer & Product"),

    //Deprecated, just to keep app work with old projects
    OLD_SUPPORT("Support Program"),
    OLD_RD_PRODUCT("R&D Product"),
    OLD_RD_COMPOSITE("R&D Composite"),
    OLD_RD("R&D"),
    OLD_MINOR_PROGRAM("Minor Program"),
    OLD_MAJOR_PROGRAM("Major Program"),
    OLD_MINOR("Minor"),
    OLD_MAINTENANCE("Maintenance"),
    OLD_COMPOSITE("Composite");
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
