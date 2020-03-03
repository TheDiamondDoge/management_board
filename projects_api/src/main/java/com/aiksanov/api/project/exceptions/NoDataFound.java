package com.aiksanov.api.project.exceptions;

public class NoDataFound extends RuntimeException {
    public NoDataFound(String msg) {
        super(msg);
    }

    public NoDataFound() {
        super("No data found");
    }
}
