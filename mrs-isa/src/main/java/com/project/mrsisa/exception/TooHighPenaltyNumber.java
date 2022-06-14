package com.project.mrsisa.exception;

public class TooHighPenaltyNumber extends Exception{
    private static final long serialVersionUID = 1791564636123821405L;

    private String resourceId;

    public TooHighPenaltyNumber(String message) {
        super(message);
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
