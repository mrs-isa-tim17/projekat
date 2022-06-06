package com.project.mrsisa.dto.client;

import com.project.mrsisa.domain.*;

public class SubscriebedEntitiesDTO {

    private long offerId;
    private String offerName;
    private String description;
    private String img;
    private OfferType offerType;

    public SubscriebedEntitiesDTO() {
    }

    public SubscriebedEntitiesDTO(Offer o) {
        this.offerId = o.getId();
        this.offerName = o.getName();
        this.description = o.getDescription();
        if (o instanceof Cottage)
            this.offerType = OfferType.COTTAGE;
        else if (o instanceof Ship)
            this.offerType = OfferType.SHIP;
        else if (o instanceof Adventure)
            this.offerType = OfferType.ADVENTURE;
        if (o.getImages().size()>0){
            this.img = o.getImages().get(0).getPath();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }
}
