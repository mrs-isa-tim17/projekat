package com.project.mrsisa.dto;

import java.time.LocalDate;

public class AdminFullBusinessReportDTO {
	private String offerName;
	private String offerType;
	private LocalDate startDate;
	private LocalDate endDate;
	private double price;
	private double percentage;
	private double income;
	private String ownerName;
	private String ownerSurname;
	
	public AdminFullBusinessReportDTO(){
		
	}	
	
	public AdminFullBusinessReportDTO(String offername, String offerType, LocalDate startDate, LocalDate endDate,
			double price, double percentage, double income, String ownerName, String ownerSurname) {
		super();
		this.offerName = offername;
		this.offerType = offerType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.percentage = percentage;
		this.income = income;
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offername) {
		this.offerName = offername;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSurname() {
		return ownerSurname;
	}

	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}
	
}
