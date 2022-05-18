package com.project.mrsisa.dto;

import java.time.LocalDateTime;

public class StartEndDateDTO {
	private LocalDateTime start;
	private LocalDateTime end;
	private String title;
	private String color;
	
	
	public StartEndDateDTO() {
		
	}
	
	public StartEndDateDTO(LocalDateTime startDate, LocalDateTime endDate, String eventName) {
		super();
		this.start = startDate;
		this.end = endDate;
		this.color = "";
		this.title = eventName;
		
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime startDate) {
		this.start = startDate;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime endDate) {
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
