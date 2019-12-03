package com.aiksanov.api.project.util.enums;

public enum BlcRoles {
    PM("pm"),
    PMO("pmo"),
    SALES("sales");

    private String roleName;

    BlcRoles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
