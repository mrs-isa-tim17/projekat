package com.project.mrsisa.domain;

public enum UserTypeLoyaltyPoints {
	
	CLIENT(0),
	OFFERER(1);

	
	private final int value;

	UserTypeLoyaltyPoints(int value) {
        this.value = value;
    }

    public static UserTypeLoyaltyPoints valueOf(int value) {
    	switch (value) {
    	case 1:
    		return UserTypeLoyaltyPoints.OFFERER;
    	default:
    		return UserTypeLoyaltyPoints.CLIENT;
    	}
    }
    
    
    public int getValue() {
        return value;
    }

}
