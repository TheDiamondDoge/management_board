package com.aiksanov.api.project.util.enums.actions;

public enum ActionsRegistryVals {
    NONE(0),
    ACTION(1),
    INFORMATION(2),
    DECISION(3);
    private int value;

    ActionsRegistryVals(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
