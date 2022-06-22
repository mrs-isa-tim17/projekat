package com.project.mrsisa.dto.simple_user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CottageFilterParamsDTO extends PaginationDTO{
    private double longitude;
    private double latitude;
    private LocalDateTime dateFrom;
    private LocalDateTime dateUntil;
    private String ratingRelOp;
    private int rating;
    private String roomsRelOp;
    private double numberOfRooms;
    private String bedsRelOp;
    private double numberOfBed;
    private String priceRelOp;
    private double price;
    private String searchBy;
    private String sortBy;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public CottageFilterParamsDTO() {
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

    public String getRoomsRelOp() {
        return roomsRelOp;
    }

    public void setRoomsRelOp(String roomsRelOp) {
        this.roomsRelOp = roomsRelOp;
    }

    public double getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getBedsRelOp() {
        return bedsRelOp;
    }

    public void setBedsRelOp(String bedsRelOp) {
        this.bedsRelOp = bedsRelOp;
    }

    public double getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(double numberOfBed) {
        this.numberOfBed = numberOfBed;
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

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }
}
