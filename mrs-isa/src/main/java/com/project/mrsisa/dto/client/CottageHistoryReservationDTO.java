package com.project.mrsisa.dto.client;

import com.project.mrsisa.converter.DateToStringConverter;
import com.project.mrsisa.domain.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CottageHistoryReservationDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private boolean quickReservation;
    private boolean canceled;
    private double price;
    private String name;
    private double longitude;
    private double latitude;
    private String description;
    private List<String> images;
    private boolean reviewed;

    public CottageHistoryReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isQuickReservation() {
        return quickReservation;
    }

    public void setQuickReservation(boolean quickReservation) {
        this.quickReservation = quickReservation;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public CottageHistoryReservationDTO(Reservation r) {
        this.id = r.getOffer().getId();
        DateToStringConverter converter = new DateToStringConverter();
        this.startDate = converter.convert(r.getStartDate());
        this.endDate = converter.convert(r.getEndDate());
        this.quickReservation = r.isQuick();
        this.canceled = r.isCanceled();
        this.price = r.getPrice();
        this.name = r.getOffer().getName();
        this.longitude = r.getOffer().getAddress().getLongitude();
        this.latitude = r.getOffer().getAddress().getLatitude();
        this.description = r.getOffer().getDescription();
        this.images = new ArrayList<String>();
        if (r.getOffer().getImages().size() != 0){
            images.add(r.getOffer().getImages().get(0).getPath());
        }
        this.reviewed = r.isReviewed();
    }
}
