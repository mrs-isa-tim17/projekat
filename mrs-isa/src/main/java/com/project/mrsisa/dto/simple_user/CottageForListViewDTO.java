package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.Cottage;

public class CottageForListViewDTO extends SizeOfListDTO{

    private int roomQuantity;
    private int bedQuantity;
    private double longitude;
    private double latitude;
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;

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


    public CottageForListViewDTO() {
    }

    public CottageForListViewDTO(Cottage cottage) {
        this.roomQuantity=cottage.getRoomQuantity();
        this.bedQuantity=cottage.getBedQuantity();
        this.name=cottage.getName();
        this.description=cottage.getDescription();
        this.id=cottage.getId();
        this.latitude = cottage.getAddress().getLatitude();
        this.longitude = cottage.getAddress().getLongitude();
        if (cottage.getImages().size() != 0){
            this.image = cottage.getImages().get(0).getPath();
        }
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
