package com.aiksanov.api.project.util.enums.actions;

public enum EditTypes {
    EDIT("edit"),
    SAVE("save");
    private String type;

    EditTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
