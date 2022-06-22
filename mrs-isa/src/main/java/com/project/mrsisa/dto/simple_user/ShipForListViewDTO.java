package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Ship;

public class ShipForListViewDTO extends SizeOfListDTO {

    private double longitude;
    private double latitude;
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private double maxSpeed;
    private int capacity;


    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    private double mark;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public ShipForListViewDTO() {
    }

    public ShipForListViewDTO(Ship ship) {
        this.name=ship.getName();
        this.description=ship.getDescription();
        this.id=ship.getId();
        this.latitude = ship.getAddress().getLatitude();
        this.longitude = ship.getAddress().getLongitude();
        if (ship.getImages().size() != 0){
            this.image = ship.getImages().get(0).getPath();
        }
        this.capacity = ship.getCapacity();
        this.maxSpeed = ship.getMaxSpeed();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
