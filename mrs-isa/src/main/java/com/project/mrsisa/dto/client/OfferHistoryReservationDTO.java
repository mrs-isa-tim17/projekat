package com.project.mrsisa.dto.client;

import com.project.mrsisa.converter.DateToStringConverter;
import com.project.mrsisa.converter.LocalDateTimeToString;
import com.project.mrsisa.converter.StringToDateConverter;
import com.project.mrsisa.converter.StringToLocalDateTime;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.simple_user.SizeOfListDTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OfferHistoryReservationDTO extends SizeOfListDTO {
    private long reservationId;
    private Long id;
    private int offerType;
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

    public OfferHistoryReservationDTO() {
        images = new ArrayList<>();
        reservationId = -1;
        id = -1L;
        offerType = 0;
        startDate = "2000.01.01 20:00";
        endDate ="2000.01.01 20:00";
        quickReservation = false;
        canceled = false;
        price = 0;
        name = "";
        latitude = 0;
        longitude = 0;
        description = "";
        reviewed = false;
    }
    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public int getOfferType() {
        return offerType;
    }

    public void setOfferType(int offerType) {
        this.offerType = offerType;
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

    public LocalDateTime getStartDateLocalDate() {
        StringToLocalDateTime converter = new StringToLocalDateTime();
        return converter.format(startDate);
    }

    public long getDuration(){
        return Duration.between(getStartDateLocalDate(), getEndDateLocalDate()).toHours();
    }

    public LocalDateTime getEndDateLocalDate() {
        StringToLocalDateTime converter = new StringToLocalDateTime();
        return converter.format(endDate);
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

    public OfferHistoryReservationDTO(Reservation r) {
        this.reservationId = r.getId();
        this.id = r.getOffer().getId();
        LocalDateTimeToString converter = new LocalDateTimeToString();
        this.startDate = converter.format(r.getStartDateTime());
        this.endDate = converter.format(r.getEndDateTime());
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
        this.offerType = r.getOfferType().getValue();

    }
}
