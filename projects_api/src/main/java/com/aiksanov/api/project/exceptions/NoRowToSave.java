package com.aiksanov.api.project.exceptions;

public class NoRowToSave extends Exception {
    public NoRowToSave() {
        super("Blc row is not found");
    }
}
