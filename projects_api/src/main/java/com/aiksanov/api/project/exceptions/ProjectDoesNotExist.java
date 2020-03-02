package com.aiksanov.api.project.exceptions;

public class ProjectDoesNotExist extends Exception {
    public ProjectDoesNotExist() {
        super("Project doesn`t exist");
    }

    public ProjectDoesNotExist(String msg) {
        super(msg);
    }
}
