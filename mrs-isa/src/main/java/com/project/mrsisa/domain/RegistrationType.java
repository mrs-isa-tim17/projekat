package com.project.mrsisa.domain;

public enum RegistrationType {
	COTTAGE_OWNER(0),
	SHIP_OWNER(1),
	FISHING_INSTRUCTOR(2);
	
	private final int value;

	RegistrationType(int value) {
        this.value = value;
    }

    public static RegistrationType valueOf(int value) {
    	switch (value) {
    	case 0:
    		return RegistrationType.COTTAGE_OWNER;
    	case 1:
    		return RegistrationType.SHIP_OWNER;
    	default:
    		return RegistrationType.FISHING_INSTRUCTOR;
    	}
    }
    
    
    public int getValue() {
        return value;
    }
}
