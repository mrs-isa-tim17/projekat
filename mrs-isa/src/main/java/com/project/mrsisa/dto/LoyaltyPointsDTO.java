package com.project.mrsisa.dto;

import java.time.LocalDate;

public class LoyaltyPointsDTO {

	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private double points;
	private String type;
	
	public LoyaltyPointsDTO() {
		
	}
	
	public LoyaltyPointsDTO(Long id, LocalDate startDate, LocalDate endDate, double points, String type) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.points = points;
		this.type = type;
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
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
