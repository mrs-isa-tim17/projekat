package com.project.mrsisa.dto.owner;

public class ReservationMonthlyGraphDTO {
    String offerName;
    int numbers;

    public ReservationMonthlyGraphDTO(String offerName, int numbers) {
        this.offerName = offerName;
        this.numbers = numbers;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
}
