package com.aiksanov.api.project.util.enums.actions;

public enum ActionPriorityVals {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3);
    private int value;

    ActionPriorityVals(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
