package com.project.mrsisa.dto.ship;

import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.NavigationEquipment;
import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.ShipOwner;
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
    private List<String> images;
   /* private NavigationEquipment navigationEquipment;
    private int capacity;
    private ShipOwner owner;

*/
    public FindShipDTO() {
        super();
    }

    public FindShipDTO(Ship ship, List<Image> imagesShips) {
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