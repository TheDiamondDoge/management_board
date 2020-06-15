package com.aiksanov.api.project.exceptions;

public class NoRowToSaveException extends Exception {
    public NoRowToSaveException() {
        super("Blc row is not found");
    }
}
