package com.project.mrsisa.domain;

public enum RegistrationType {
	COTTAGE_OWNER(3),
	SHIP_OWNER(4),
	FISHING_INSTRUCTOR(5);
	
	private final int value;

	RegistrationType(int value) {
        this.value = value;
    }

    public static RegistrationType valueOf(int value) {
    	switch (value) {
    	case 3:
    		return RegistrationType.COTTAGE_OWNER;
    	case 4:
    		return RegistrationType.SHIP_OWNER;
    	default:
    		return RegistrationType.FISHING_INSTRUCTOR;
    	}
    }
    
    
    public int getValue() {
        return value;
    }
}
