package com.project.mrsisa.domain;

public enum OfferType {
    COTTAGE(0),
    SHIP(1),
    ADVENTURE(2);

    private final int value;

    OfferType(int value) {
        this.value = value;
    }

    public static OfferType valueOf(int value) {
        switch (value) {
            case 2:
                return OfferType.ADVENTURE;
            case 1:
                return OfferType.SHIP;
            default:
                return OfferType.COTTAGE;
        }
    }

    public int getValue() {
        return value;
    }
}
