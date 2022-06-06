package com.project.mrsisa.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReserveEntityDTO {

    private long clientId;
    private long offerId;
    private String offerType;
    private List<String> chosenAdditionalServices;
    private LocalDateTime fromDate;
    private LocalDateTime untilDate;
    private boolean shipOwnerPresent;

    public boolean isShipOwnerPresent() {
        return shipOwnerPresent;
    }

    public void setShipOwnerPresent(boolean shipOwnerPresent) {
        this.shipOwnerPresent = shipOwnerPresent;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public List<String> getChosenAdditionalServices() {
        return chosenAdditionalServices;
    }

    public void setChosenAdditionalServices(List<String> chosenAdditionalServices) {
        this.chosenAdditionalServices = chosenAdditionalServices;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate.plusHours(2);
    }

    public LocalDateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDateTime untilDate) {
        this.untilDate = untilDate.plusHours(2);
    }
}
