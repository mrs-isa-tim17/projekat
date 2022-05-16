package com.project.mrsisa.dto.simple_user;

import java.time.LocalDateTime;

public class ShipFilterParamsDTO {
    private double longitude;
    private double latitude;
    private LocalDateTime dateFrom;
    private LocalDateTime dateUntil;
    private String ratingRelOp;
    private int rating;
    private String capacityRelOp;
    private double capacity;
    private String speedRelOp;
    private double speed;
    private String priceRelOp;
    private double price;

    public ShipFilterParamsDTO() {
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

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(LocalDateTime dateUntil) {
        this.dateUntil = dateUntil;
    }

    public String getRatingRelOp() {
        return ratingRelOp;
    }

    public void setRatingRelOp(String ratingRelOp) {
        this.ratingRelOp = ratingRelOp;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCapacityRelOp() {
        return capacityRelOp;
    }

    public void setCapacityRelOp(String capacityRelOp) {
        this.capacityRelOp = capacityRelOp;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getSpeedRelOp() {
        return speedRelOp;
    }

    public void setSpeedRelOp(String speedRelOp) {
        this.speedRelOp = speedRelOp;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getPriceRelOp() {
        return priceRelOp;
    }

    public void setPriceRelOp(String priceRelOp) {
        this.priceRelOp = priceRelOp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
