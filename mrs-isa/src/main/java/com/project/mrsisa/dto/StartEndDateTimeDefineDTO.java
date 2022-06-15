package com.project.mrsisa.dto;

import java.time.LocalDateTime;

public class StartEndDateTimeDefineDTO {
	private LocalDateTime start;
	private LocalDateTime end;
	private String offerType;
	
	
	public StartEndDateTimeDefineDTO() {
		
	}
	
	public StartEndDateTimeDefineDTO(LocalDateTime start, LocalDateTime end, String offerType) {
		super();
		this.start = start;
		this.end = end;
		this.offerType = offerType;
	}

	public StartEndDateTimeDefineDTO(LocalDateTime start, LocalDateTime end){
		super();
		this.start = start;
		this.end = end;
		this.offerType ="";
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
}
