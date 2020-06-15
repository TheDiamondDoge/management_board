package com.aiksanov.api.project.exceptions;

public class ProjectDoesNotExistException extends RuntimeException {
    public ProjectDoesNotExistException() {
        super("Project does not exist");
    }

    public ProjectDoesNotExistException(String msg) {
        super(msg);
    }
}
