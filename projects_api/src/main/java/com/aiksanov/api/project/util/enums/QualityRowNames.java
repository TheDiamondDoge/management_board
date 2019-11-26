package com.aiksanov.api.project.util.enums;

public enum QualityRowNames {
    QUALITY ("quality"),
    DEFECTS ("defects"),
    BACKLOG ("backlog"),
    EXECUTION ("execution"),
    RATE ("rate");

    private String title;

    QualityRowNames(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
