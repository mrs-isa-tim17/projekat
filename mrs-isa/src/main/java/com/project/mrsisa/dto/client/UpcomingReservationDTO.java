package com.project.mrsisa.dto.client;

import com.project.mrsisa.domain.Reservation;

import java.time.LocalDateTime;

public class UpcomingReservationDTO {

    private long offerId;
    private long reservationId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private boolean quick;
    private boolean canceled;
    private String offerName;
    private String description;
    private String img;

    public UpcomingReservationDTO(Reservation r) {
        this.offerId = r.getOffer().getId();
        this.reservationId = r.getId();
        this.startDate = r.getStartDateTime();
        this.endDate = r.getEndDateTime();
        this.price = r.getPrice();
        this.canceled = r.isCanceled();
        this.quick = r.isQuick();
        this.offerName = r.getOffer().getName();
        this.description = r.getOffer().getDescription();
        if (r.getOffer().getImages().size()>0){
            this.img = r.getOffer().getImages().get(0).getPath();
        }
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isQuick() {
        return quick;
    }

    public void setQuick(boolean quick) {
        this.quick = quick;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
