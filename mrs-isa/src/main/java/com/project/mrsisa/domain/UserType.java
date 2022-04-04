package com.project.mrsisa.domain;

public enum UserType {
	
	REGULAR(0),
	SILVER(1),
	GOLD(2);
	
	private final int value;

	UserType(int value) {
        this.value = value;
    }

    public static UserType valueOf(int value) {
    	switch (value) {
    	case 2:
    		return UserType.GOLD;
    	case 1:
    		return UserType.SILVER;
    	default:
    		return UserType.REGULAR;
    	}
    }
    
    
    public int getValue() {
        return value;
    }

}
