package com.project.mrsisa.dto.ship;

import com.project.mrsisa.domain.*;

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
    private List<String> cancelConditions;
    private int capacity;

    private String ownersEmail;

    private String ownersName;
    private String ownersSurname;
    private List<String> days;
    private String p1;
    private String p2;
    private String p3;
    private String p4;

    // private NavigationEquipment navigationEquipment;


    public List<String> getBehavioralRules() {
        return behavioralRules;
    }

    public void setBehavioralRules(List<String> behavioralRules) {
        this.behavioralRules = behavioralRules;
    }

    public List<String> getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(List<String> cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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


    public FindShipDTO() {
        super();
    }

    public FindShipDTO(Ship ship, List<Image> imagesShips, List<BehaviorRule> rules, List<CancelCondition> conditions) {
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

        images = new ArrayList<String>();
        for(Image i : imagesShips){
            images.add(i.getPath());
        }
        behavioralRules = new ArrayList<String>();
        for(BehaviorRule br : rules) {
            behavioralRules.add(br.getText());
        }

        cancelConditions = new ArrayList<String>();
        for(CancelCondition cc : conditions) {
            this.days.add(String.valueOf(cc.getDays()));
            switch (cc.getDays()) {
                case 5:
                    this.p1 = String.valueOf(cc.getPrecent());
                    this.cancelConditions.add("Za otkazni rok od 0 - 5 dana pre početka događaja, naplaćuje se " + this.p1 + "od ukupne cene rezervacije.");
                    break;
                case 10:
                    this.p2 = String.valueOf(cc.getPrecent());
                    this.cancelConditions.add("Za otkazni rok od 6 - 10 dana pre početka događaja, naplaćuje se " + this.p2 + "od ukupne cene rezervacije.");
                    break;
                case 15:
                    this.p3 = String.valueOf(cc.getPrecent());
                    this.cancelConditions.add("Za otkazni rok od 11 - 15 dana pre početka događaja, naplaćuje se " + this.p3 + "od ukupne cene rezervacije.");
                    break;
                case 20:
                    this.p4 = String.valueOf(cc.getPrecent());
                    this.cancelConditions.add("Za otkazni rok od 16 i više dana pre početka događaja, naplaćuje se " + this.p4 + "od ukupne cene rezervacije.");
                    break;
                default:
                    break;
            }
        }


       /* navigationEquipment = ship.getNavigationEquipment();
        capacity = ship.getCapacity();
        owner = ship.getOwner();*/
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


}
