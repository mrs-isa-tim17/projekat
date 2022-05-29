package com.project.mrsisa.dto.ship;

import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FindShipsByOwnerDTO {
    private String ownersEmail;
    private int capacity;
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private String description;
    private boolean deleted;
    private List<String> images;
    private double price;
    @Autowired
    public PricelistService pricelistService;

    public FindShipsByOwnerDTO(Ship ship, List<Image> imagesShips, double price){
        this.capacity=ship.getCapacity();
        this.longitude = ship.getAddress().getLongitude();
        this.latitude = ship.getAddress().getLatitude();
        this.name=ship.getName();
        this.description=ship.getDescription();
        this.deleted=ship.isDeleted();
        this.id=ship.getId();

        images = new ArrayList<String>();
        for(Image i : imagesShips) {
            images.add(i.getPath());
        }

        this.price = price;
    }


    public String getOwnersEmail() {
        return ownersEmail;
    }

    public void setOwnersEmail(String ownersEmail) {
        this.ownersEmail = ownersEmail;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
