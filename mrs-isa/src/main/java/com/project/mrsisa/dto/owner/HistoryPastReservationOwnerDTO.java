package com.project.mrsisa.dto.owner;

import com.project.mrsisa.converter.DateToStringConverter;
import com.project.mrsisa.converter.StringToDateConverter;
import com.project.mrsisa.domain.Reservation;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryPastReservationOwnerDTO {
    private Long reservationId;
    private Long id;
    private int offerType;
    private String startDate;
    private String endDate;
    private boolean quickReservation;
    private boolean canceled;
    private double price;
    private String name;
    //private int guestsNumber;
    private Long clientId;
    private double longitude;
    private double latitude;
    private String description;
    private List<String> images;
    private boolean reviewed;

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }



    public HistoryPastReservationOwnerDTO() {
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

   /* public int getGuestsNumber(){return guestsNumber;}

    public void setGuestsNumber(int guestsNumber){this.guestsNumber=guestsNumber;}*/

    public Long getClientId(){return clientId;}

    public void setClientId(Long clientId){this.clientId=clientId;}

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

    public LocalDate getStartDateLocalDate() {
        StringToDateConverter converter = new StringToDateConverter();
        return converter.convert(startDate);
    }

    public long getDuration(){
        return Duration.between(getStartDateLocalDate().atStartOfDay(), getEndDateLocalDate().atStartOfDay()).toDays();
    }

    public LocalDate getEndDateLocalDate() {
        StringToDateConverter converter = new StringToDateConverter();
        return converter.convert(endDate);
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

    public HistoryPastReservationOwnerDTO(Reservation r) {
        this.reservationId = r.getId();
        this.id = r.getOffer().getId();
        System.out.println("id"+this.id);
        DateToStringConverter converter = new DateToStringConverter();
        this.startDate = converter.convert(r.getStartDateTime());
        System.out.println("id"+this.startDate);

        this.endDate = converter.convert(r.getEndDateTime());
        System.out.println("id"+this.endDate);

        this.quickReservation = r.isQuick();
        this.canceled = r.isCanceled();
        this.price = r.getPrice();

       System.out.println("klijent" + r.getClient().getId());
        this.clientId = r.getClient().getId();
        //this.guestsNumber = r.getGuestsNumber();
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
