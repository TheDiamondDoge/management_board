package com.aiksanov.api.project.util.enums.actions;

public enum ActionsStateVals {
    NONE(0),
    ACTIVE(1),
    POSTPONED(2),
    CLOSED(3),
    NOT_OCCUR(4);
    private int value;

    ActionsStateVals(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
