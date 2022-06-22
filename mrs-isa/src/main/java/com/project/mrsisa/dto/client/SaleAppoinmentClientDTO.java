package com.project.mrsisa.dto.client;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.SaleAppointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleAppoinmentClientDTO {
    private Long id;
    private LocalDateTime startDateTime;

    private double longitude;
    private double latitude;

    private double duration;
    private int peopleQuantity;

    private List<String> additionalServices;
    private double price;

    public SaleAppoinmentClientDTO(SaleAppointment sa) {
        id = sa.getId();
        startDateTime = sa.getStartSaleDate();
        longitude =sa.getAddress().getLongitude();
        latitude = sa.getAddress().getLatitude();
        duration = sa.getDuration();
        peopleQuantity = sa.getPeopleQuantity();
        price = sa.getDiscount();
        setAdditionalServicesFromAdditionalServiceObject(sa.getAdditionalServices());
    }


    public void setAdditionalServicesFromAdditionalServiceObject(List<AdditionalServices> additionalServices) {
        this.additionalServices = new ArrayList<String>();
        for (AdditionalServices i : additionalServices){
            this.additionalServices.add(i.getName());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getPeopleQuantity() {
        return peopleQuantity;
    }

    public void setPeopleQuantity(int peopleQuantity) {
        this.peopleQuantity = peopleQuantity;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<String> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
