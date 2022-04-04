package com.project.mrsisa.domain;

import java.time.LocalDate;

public class LoyaltyPoints {
	private double points;
	private LocalDate startDate;
	private LocalDate endDate;
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
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
	
	
	

}
