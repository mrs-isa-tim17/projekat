package com.project.mrsisa.dto.client;

public class ComplaintOnOfferDTO {
    private long offerId;
    private long clientID;
    private String text;
    private int offerType;

    public ComplaintOnOfferDTO() {
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOfferType() {
        return offerType;
    }

    public void setOfferType(int offerType) {
        this.offerType = offerType;
    }
}
