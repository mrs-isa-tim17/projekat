package com.project.mrsisa.dto;

import java.time.LocalDateTime;

public class StartEndDateTimeDefineDTO {
	private LocalDateTime start;
	private LocalDateTime end;
	
	
	public StartEndDateTimeDefineDTO() {
		
	}
	
	public StartEndDateTimeDefineDTO(LocalDateTime start, LocalDateTime end) {
		super();
		this.start = start;
		this.end = end;
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
	

}
