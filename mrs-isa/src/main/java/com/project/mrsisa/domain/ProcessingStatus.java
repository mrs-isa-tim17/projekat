package com.project.mrsisa.domain;

public enum ProcessingStatus {
	UNPROCESSED(0),
	APPROVED(1),
	REJECTED(2);
	
	private final int value;

	ProcessingStatus(int value) {
        this.value = value;
    }

    public static ProcessingStatus valueOf(int value) {
    	switch (value) {
    	case 1:
    		return ProcessingStatus.APPROVED;
    	case 2:
    		return ProcessingStatus.REJECTED;
    	default:
    		return ProcessingStatus.UNPROCESSED;
    	}
    }
    
    
    public int getValue() {
        return value;
    }
}
