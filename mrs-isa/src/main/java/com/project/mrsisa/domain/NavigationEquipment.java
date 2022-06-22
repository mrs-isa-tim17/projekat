package com.project.mrsisa.domain;


public enum NavigationEquipment {
	
	GPS(0),
	RADAR(1),
	VHF_RADIO(2),
	FISHFINDER(3);
	
	private final int value;

	NavigationEquipment(int value) {
        this.value = value;
    }

    public static NavigationEquipment valueOf(int value) {
    	switch (value) {
    	case 0:
    		return NavigationEquipment.GPS;
    	case 1:
    		return NavigationEquipment.RADAR;
    	case 2:
    		return NavigationEquipment.VHF_RADIO;
    	default:
    		return NavigationEquipment.FISHFINDER;
    	}
    }

    
    
    public int getValue() {
        return value;
    }


}
