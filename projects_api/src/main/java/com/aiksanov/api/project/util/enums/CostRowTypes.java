package com.aiksanov.api.project.util.enums;

public enum CostRowTypes {
    CHARGE(0), CAPEX(1);
    private int value;

    CostRowTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
