package com.project.mrsisa.dto.owner;

import java.util.List;

public class ReservationsYearlyGraphDTO {
    private List<String> monthYear;
    private List<Integer> number;
    private String offerName;

    public ReservationsYearlyGraphDTO(List<String> monthYear, List<Integer> number, String offerName) {
        this.monthYear = monthYear;
        this.number = number;
        this.offerName = offerName;
    }

    public List<String> getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(List<String> monthYear) {
        this.monthYear = monthYear;
    }

    public List<Integer> getNumber() {
        return number;
    }

    public void setNumber(List<Integer> number) {
        this.number = number;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }
}
