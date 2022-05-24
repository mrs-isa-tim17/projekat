package com.project.mrsisa.domain;

public enum ComplaintStatus {
	UNPROCESSED(0),
	ANSWERED(1);
	
	private final int value;

	ComplaintStatus(int value) {
        this.value = value;
    }

    public static ComplaintStatus valueOf(int value) {
    	switch (value) {
    	case 1:
    		return ComplaintStatus.ANSWERED;

    	default:
    		return ComplaintStatus.UNPROCESSED;
    	}
    }
    
    
    public int getValue() {
        return value;
    }
}
