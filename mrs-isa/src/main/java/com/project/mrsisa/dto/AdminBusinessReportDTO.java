package com.project.mrsisa.dto;

import java.time.LocalDate;

public class AdminBusinessReportDTO {
	private LocalDate startDate;
	private LocalDate endDate;
	private double income;
	
	
	public AdminBusinessReportDTO(){
		
	}	
	
	public AdminBusinessReportDTO(LocalDate startDate, LocalDate endDate, double income) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.income = income;
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
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}

}
