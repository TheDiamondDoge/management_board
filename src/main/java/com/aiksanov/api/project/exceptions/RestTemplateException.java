package com.aiksanov.api.project.exceptions;

import java.util.Date;

public class RestTemplateException extends Exception {
    private Date timestamp;
    private String message;
    private int status;
    private String error;
    private String path;

    public RestTemplateException() {
    }

    public RestTemplateException(String message) {
        super(message);
    }

    public RestTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestTemplateException(Throwable cause) {
        super(cause);
    }

    public RestTemplateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
