package com.project.mrsisa.dto;

import java.time.LocalDate;

public class LoyaltyScaleDTO {
	private long Id;
	private int discount;
	private LocalDate startDate;
	private LocalDate endDate;
	private int trashold;
	private String userType;
	private String role;
	
	
	public LoyaltyScaleDTO() {
		
	}
	
	public LoyaltyScaleDTO(long id, int discount, LocalDate startDate, LocalDate endDate, int trashold, String userType,
			String role) {
		super();
		Id = id;
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trashold = trashold;
		this.userType = userType;
		this.role = role;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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

	public int getTrashold() {
		return trashold;
	}

	public void setTrashold(int trashold) {
		this.trashold = trashold;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
