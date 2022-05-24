package com.project.mrsisa.dto;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.ExperienceReview;

public class ExperienceReviewDTO {
    private String text;
    private int rate;
    private String clientName;
    private String clientSurname;

    public ExperienceReviewDTO(ExperienceReview er,Client client) {
        this.text = er.getText();
        this.rate = er.getRate();
        this.clientName = client.getName();
        this.clientSurname = client.getSurname();

        //this.clientName = er.getClient().getName();
        //this.clientSurname = er.getClient().getSurname();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }
}
