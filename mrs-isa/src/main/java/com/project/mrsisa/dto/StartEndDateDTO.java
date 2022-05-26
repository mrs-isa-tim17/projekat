package com.project.mrsisa.dto;

import java.time.LocalDateTime;

public class StartEndDateDTO {
	private String start;
	private String end;
	private String title;
	private String color;
	
	
	public StartEndDateDTO() {
		
	}
	
	public StartEndDateDTO(String startDate, String endDate, String eventName) {
		super();
		this.start = startDate;
		this.end = endDate;
		this.color = "";
		this.title = eventName;
		
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String startDate) {
		this.start = startDate;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String endDate) {
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

	@Override
	public String toString() {
		return "StartEndDateDTO [start=" + start + ", end=" + end + ", title=" + title + ", color=" + color + "]";
	}

	
	
	
}
