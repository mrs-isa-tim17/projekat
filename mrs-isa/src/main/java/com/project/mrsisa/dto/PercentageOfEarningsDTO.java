package com.project.mrsisa.dto;

import java.time.LocalDate;

public class PercentageOfEarningsDTO {
	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private double percentage;
	
	
	public PercentageOfEarningsDTO() {
		
	}
	
	public PercentageOfEarningsDTO(Long id, LocalDate startDate, LocalDate endDate, double percentage) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.percentage = percentage;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public double getPercentage() {
		return percentage;
	}


	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
}
