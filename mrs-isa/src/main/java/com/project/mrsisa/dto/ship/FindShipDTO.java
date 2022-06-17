package com.project.mrsisa.dto.ship;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.domain.NavigationEquipment;
import com.project.mrsisa.service.PricelistService;


import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class FindShipDTO {
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private String description;
    private boolean deleted;
    private String type;
    private double length;
    private String engineDesignation;
    private double enginePower;
    private double maxSpeed;
    private List<String> behavioralRules;
    private List<String> images;
    private List<String> fishingEquipment;
    private String navigationEquipment;
    private int capacity;
    private Long ownerId;
    private String ownersEmail;

    private String ownersName;
    private String ownersSurname;
    private List<Integer> days;
    private List<Double> percents;

    private double price;
    private LocalDate priceStartDate;
    public PricelistService pricelistService;
    private List<String> additionalServices;
    private Long priceListId;

    public List<String> getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(List<String> fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(String navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }

    public String getOwnersEmail() {
        return ownersEmail;
    }

    public void setOwnersEmail(String ownersEmail) {
        this.ownersEmail = ownersEmail;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public String getOwnersSurname() {
        return ownersSurname;
    }

    public void setOwnersSurname(String ownersSurname) {
        this.ownersSurname = ownersSurname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPriceStartDate() {
        return priceStartDate;
    }

    public void setPriceStartDate(LocalDate priceStartDate) {
        this.priceStartDate = priceStartDate;
    }

    public PricelistService getPricelistService() {
        return pricelistService;
    }

    public void setPricelistService(PricelistService pricelistService) {
        this.pricelistService = pricelistService;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<String> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public List<Double> getPercents() {
        return percents;
    }

    public void setPercents(List<Double> percents) {
        this.percents = percents;
    }
    // private NavigationEquipment navigationEquipment;


    public List<String> getBehavioralRules() {
        return behavioralRules;
    }

    public void setBehavioralRules(List<String> behavioralRules) {
        this.behavioralRules = behavioralRules;
    }


    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Long priceListId) {
        this.priceListId = priceListId;
    }

    public FindShipDTO() {
        super();
    }

    public FindShipDTO(Ship ship, List<Image> imagesShips, List<BehaviorRule> rules, List<CancelCondition> conditions,List<FishingEquipment> fishEquipment,Pricelist pricelist, List<AdditionalServices> addServices) {
        id = ship.getId();
        name = ship.getName();
        longitude = ship.getAddress().getLongitude();
        latitude = ship.getAddress().getLatitude();
        description = ship.getDescription();
        deleted = ship.isDeleted();
        type = ship.getType();
        length = ship.getLength();
        engineDesignation = ship.getEngineDesignation();
        enginePower = ship.getEnginePower();
        maxSpeed = ship.getMaxSpeed();
        capacity = ship.getCapacity();
        priceListId = pricelist.getId();
        ownerId = ship.getOwner().getId();

        images = new ArrayList<String>();
        for(Image i : imagesShips){
            images.add(i.getPath());
        }
        behavioralRules = new ArrayList<String>();
        for(BehaviorRule br : rules) {
            behavioralRules.add(br.getText());
        }

        days = new ArrayList<Integer>();
        percents = new ArrayList<Double>();
        for(CancelCondition cc : conditions) {
            this.days.add(cc.getDays());
            this.percents.add(cc.getPrecent());
            }

        navigationEquipment = getNavEq(ship.getNavigationEquipment().getValue());

        fishingEquipment = new ArrayList<>();
        for(FishingEquipment fe : fishEquipment){
            fishingEquipment.add(fe.getName());
        }

        additionalServices = new ArrayList<String>();
        for(AdditionalServices as : addServices){
            this.additionalServices.add(as.getName());
        }

        this.price = pricelist.getPrice();
        this.priceStartDate = pricelist.getStartDate();
    }

    public static java.lang.String getNavEq(int value) {
        switch (value) {
            case 0:
                return "GPS";
            case 1:
                return "RADAR";
            case 2:
                return "VHF_RADIO";
            default:
                return "FISHFINDER";
        }

    }
       /* navigationEquipment = ship.getNavigationEquipment();
        capacity = ship.getCapacity();
        owner = ship.getOwner();*/

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
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public String getEngineDesignation() {
        return engineDesignation;
    }
    public void setEngineDesignation(String engineDesignation) {
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    /* public NavigationEquipment getNavigationEquipment() {
        return navigationEquipment;
    }
    public void setNavigationEquipment(NavigationEquipment navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public ShipOwner getOwner() {
        return owner;
    }
    public void setOwner(ShipOwner owner) {
        this.owner = owner;
    }*/

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
