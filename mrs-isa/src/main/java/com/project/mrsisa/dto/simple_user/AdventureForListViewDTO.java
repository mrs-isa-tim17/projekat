package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.Ship;

public class AdventureForListViewDTO extends SizeOfListDTO{
    private double longitude;
    private double latitude;
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int capacity;
    private double mark;

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


    public AdventureForListViewDTO() {
    }

    public AdventureForListViewDTO(Adventure adventure) {
        this.name=adventure.getName();
        this.description=adventure.getDescription();
        this.id=adventure.getId();
        this.latitude = adventure.getAddress().getLatitude();
        this.longitude = adventure.getAddress().getLongitude();
        if (adventure.getImages().size() != 0){
            this.image = adventure.getImages().get(0).getPath();
        }
        this.capacity = adventure.getCapacity();
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
