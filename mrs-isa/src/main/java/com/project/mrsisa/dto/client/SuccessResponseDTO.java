package com.project.mrsisa.dto.client;

public class SuccessResponseDTO {

    private boolean successful;
    private String explanation;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
