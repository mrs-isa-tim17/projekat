package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.ExperienceReview;

public class ExperienceReviewDTO {
    private String clientName;
    private String text;
    private int rating;

    public ExperienceReviewDTO() {
    }

    public ExperienceReviewDTO(ExperienceReview ex) {
        text = ex.getText();
        rating = ex.getRate();
        clientName = ex.getClient().getName() + " " + ex.getClient().getSurname();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
