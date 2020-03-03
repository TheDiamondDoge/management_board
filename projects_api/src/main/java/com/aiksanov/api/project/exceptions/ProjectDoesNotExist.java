package com.aiksanov.api.project.exceptions;

public class ProjectDoesNotExist extends RuntimeException {
    public ProjectDoesNotExist() {
        super("Project does not exist");
    }

    public ProjectDoesNotExist(String msg) {
        super(msg);
    }
}
