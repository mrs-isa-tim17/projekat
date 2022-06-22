package com.project.mrsisa.dto.owner;


public class IncomePeriodGraphDTO {
    String offerName;
    double price;

    public IncomePeriodGraphDTO(String offerName, double price) {
        this.offerName = offerName;
        this.price = price;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

