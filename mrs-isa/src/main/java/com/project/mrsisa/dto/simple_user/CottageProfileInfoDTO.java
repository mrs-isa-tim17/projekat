package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Image;

import java.util.ArrayList;
import java.util.List;

public class CottageProfileInfoDTO {

    private Long id;
    private String name;

    private double longitude;
    private double latitude;

    private String description;

    private List<String> behavioralRules;
    private List<String> images;
    private List<String> additionalServices;
    private List<Double> additionalServicesPrice;

    private double price;
    private int numberOfRooms;
    private int numberOfBeds;

    private double rating;

    public CottageProfileInfoDTO() {
    }

    public CottageProfileInfoDTO(Cottage c) {
        this.id = c.getId();
        this.name = c.getName();
        this.longitude = c.getAddress().getLongitude();
        this.latitude = c.getAddress().getLatitude();
        this.description = c.getDescription();
        this.numberOfRooms = c.getRoomQuantity();
        this.numberOfBeds = c.getBedQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getBehavioralRules() {
        return behavioralRules;
    }

    public void setBehavioralRules(List<String> behavioralRules) {
        this.behavioralRules = behavioralRules;
    }

    public void setBehavioralRulesFromBehaviourRuleObject(List<BehaviorRule> behavioralRules) {
        this.behavioralRules = new ArrayList<String>();
        for (BehaviorRule i : behavioralRules){
            this.behavioralRules.add(i.getText());
        }
    }

    public void setAdditionalServicesFromAdditionalServiceObject(List<AdditionalServices> additionalServices) {
        this.additionalServices = new ArrayList<String>();
        for (AdditionalServices i : additionalServices){
            this.additionalServices.add(i.getName());
        }
        this.additionalServicesPrice = new ArrayList<Double>();
        for (AdditionalServices i : additionalServices){
            this.additionalServicesPrice.add(i.getPrice());
        }
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setImagesFromImageObjects(List<Image> images) {
        this.images = new ArrayList<String>();
        for (Image i : images){
            this.images.add(i.getPath());
        }
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

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Double> getAdditionalServicesPrice() {
        return additionalServicesPrice;
    }

    public void setAdditionalServicesPrice(List<Double> additionalServicesPrice) {
        this.additionalServicesPrice = additionalServicesPrice;
    }
}
