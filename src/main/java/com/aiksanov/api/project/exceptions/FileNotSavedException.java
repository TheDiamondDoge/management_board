package com.aiksanov.api.project.exceptions;

public class FileNotSavedException extends Exception {
    private static final String DEFAULT_MESSAGE = "File not saved";

    public FileNotSavedException() {
        super(DEFAULT_MESSAGE);
    }

    public FileNotSavedException(String message) {
        super(message);
    }
}
