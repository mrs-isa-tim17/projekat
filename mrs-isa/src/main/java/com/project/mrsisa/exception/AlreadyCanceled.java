package com.project.mrsisa.exception;

public class AlreadyCanceled extends Exception{
    private static final long serialVersionUID = 1791564636123821405L;

    private String resourceId;

    public AlreadyCanceled(String message) {
        super(message);
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}

