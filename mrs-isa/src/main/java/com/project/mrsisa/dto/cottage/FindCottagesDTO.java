package com.project.mrsisa.dto.cottage;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Pricelist;
import com.project.mrsisa.service.BehaviorRuleService;
import com.project.mrsisa.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FindCottagesDTO {
    private int roomQuantity;
    private String ownersEmail;
    private int bedQuantity;
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

    public FindCottagesDTO(Cottage cottage, List<Image> imagesCottage, double price) {
        this.roomQuantity=cottage.getRoomQuantity();
        this.bedQuantity=cottage.getBedQuantity();
        this.longitude = cottage.getAddress().getLongitude();
        this.latitude = cottage.getAddress().getLatitude();
        this.name=cottage.getName();
        this.description=cottage.getDescription();
        this.deleted=cottage.isDeleted();
        this.id=cottage.getId();


        images = new ArrayList<String>();
        for(Image i : imagesCottage) {
            images.add(i.getPath());
        }

        this.price = price;


    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public String getOwnersEmail() {
        return ownersEmail;
    }

    public void setOwnersEmail(String ownersEmail) {
        this.ownersEmail = ownersEmail;
    }

    public int getBedQuantity() {
        return bedQuantity;
    }

    public void setBedQuantity(int bedQuantity) {
        this.bedQuantity = bedQuantity;
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
