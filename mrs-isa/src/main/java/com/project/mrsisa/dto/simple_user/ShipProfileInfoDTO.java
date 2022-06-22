package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.domain.NavigationEquipment;

import java.util.ArrayList;
import java.util.List;

public class ShipProfileInfoDTO {

    private Long id;
    private java.lang.String name;

    private double longitude;
    private double latitude;

    private java.lang.String description;

    private List<java.lang.String> behavioralRules;
    private List<java.lang.String> images;
    private List<java.lang.String> additionalServices;
    private List<Double> additionalServicesPrice;

    private double price;
    private double rating;

    private int capacity;
    private List<java.lang.String> fishingEquipments;

    //osnonvne informacije o brodu
    private java.lang.String type;
    private double length;
    private java.lang.String engineDesignation;
    private double enginePower;
    private double maxSpeed;
    private NavigationEquipment navigationEquipment;

    public ShipProfileInfoDTO() {
    }

    public ShipProfileInfoDTO(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.latitude = ship.getAddress().getLatitude();
        this.longitude = ship.getAddress().getLongitude();
        this.description = ship.getDescription();
        this.capacity = ship.getCapacity();
        this.type = ship.getType();
        this.length = ship.getLength();
        this.engineDesignation = ship.getEngineDesignation();
        this.enginePower = ship.getEnginePower();
        this.maxSpeed = ship.getMaxSpeed();
        this.navigationEquipment = ship.getNavigationEquipment();
    }

    public void setBehavioralRulesFromBehaviourRuleObject(List<BehaviorRule> behavioralRules) {
        this.behavioralRules = new ArrayList<java.lang.String>();
        for (BehaviorRule i : behavioralRules){
            this.behavioralRules.add(i.getText());
        }
    }

    public void setImagesFromImageObjects(List<Image> images) {
        this.images = new ArrayList<java.lang.String>();
        for (Image i : images){
            this.images.add(i.getPath());
        }
    }

    public void setAdditionalServicesFromAdditionalServiceObject(List<AdditionalServices> additionalServices) {
        this.additionalServices = new ArrayList<java.lang.String>();
        for (AdditionalServices i : additionalServices){
            this.additionalServices.add(i.getName());
        }
        this.additionalServicesPrice = new ArrayList<Double>();
        for (AdditionalServices i : additionalServices){
            this.additionalServicesPrice.add(i.getPrice());
        }
    }

    public void setAdditionalServicesFromFishingEquipmentObject(List<FishingEquipment> fishingEquipments) {
        this.fishingEquipments = new ArrayList<java.lang.String>();
        for (FishingEquipment i : fishingEquipments){
            this.fishingEquipments.add(i.getName());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
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

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public List<java.lang.String> getBehavioralRules() {
        return behavioralRules;
    }

    public void setBehavioralRules(List<java.lang.String> behavioralRules) {
        this.behavioralRules = behavioralRules;
    }

    public List<java.lang.String> getImages() {
        return images;
    }

    public void setImages(List<java.lang.String> images) {
        this.images = images;
    }

    public List<java.lang.String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<java.lang.String> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<java.lang.String> getFishingEquipments() {
        return fishingEquipments;
    }

    public void setFishingEquipments(List<java.lang.String> fishingEquipments) {
        this.fishingEquipments = fishingEquipments;
    }

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public java.lang.String getEngineDesignation() {
        return engineDesignation;
    }

    public void setEngineDesignation(java.lang.String engineDesignation) {
        this.engineDesignation = engineDesignation;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public NavigationEquipment getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(NavigationEquipment navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }

    public List<Double> getAdditionalServicesPrice() {
        return additionalServicesPrice;
    }

    public void setAdditionalServicesPrice(List<Double> additionalServicesPrice) {
        this.additionalServicesPrice = additionalServicesPrice;
    }
}
