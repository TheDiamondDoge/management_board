package com.aiksanov.api.project.util.enums;

public enum BlcRoles {
    PM,
    PMO,
    SALES;

    public static BlcRoles getEnumByValue(String value) {
        for (BlcRoles role : values()) {
            if (role.name().equals(value.toUpperCase())) {
                return role;
            }
        }

        return null;
    }
}
