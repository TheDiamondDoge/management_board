package com.aiksanov.api.project.exceptions;

public class TooMuchFilesException extends Exception {
    private static final String DEFAULT_MESSAGE = "Max amount of files exceeded";

    public TooMuchFilesException() {
        super(DEFAULT_MESSAGE);
    }

    public TooMuchFilesException(String message) {
        super(message);
    }
}
