package com.aiksanov.api.project.util.enums.cost;

public enum CostStates {
    COMMITTED(0), RELEASED(1);
    private final int value;

    CostStates(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
