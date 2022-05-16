package com.project.mrsisa.dto;

import java.time.LocalDate;

public class StartEndDateDTO {
	private LocalDate start;
	private LocalDate end;
	private String title;
	private String color;
	
	
	public StartEndDateDTO() {
		
	}
	
	public StartEndDateDTO(LocalDate startDate, LocalDate endDate, String eventName) {
		super();
		this.start = startDate;
		this.end = endDate;
		this.color = "";
		this.title = eventName;
		
	}
	
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate startDate) {
		this.start = startDate;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate endDate) {
		this.end = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String eventName) {
		this.title = eventName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
