package com.project.mrsisa.dto.simple_user;

import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferForHomePageViewDTO {

    private long id;
    private String name;
    private String description;
    private List<String> images;

    public OfferForHomePageViewDTO(Offer offer) {
        this.id = offer.getId();
        this.name=offer.getName();
        this.description=offer.getDescription();
        this.images = new ArrayList<>();
        if (offer.getImages().size() != 0){
            for (Image i : offer.getImages()){
                this.images.add(i.getPath());
            }
        }
    }

    public OfferForHomePageViewDTO() {
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
