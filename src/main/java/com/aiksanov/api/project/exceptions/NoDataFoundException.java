package com.aiksanov.api.project.exceptions;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String msg) {
        super(msg);
    }

    public NoDataFoundException() {
        super("No data found");
    }
}
