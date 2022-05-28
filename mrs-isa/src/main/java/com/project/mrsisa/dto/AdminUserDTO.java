package com.project.mrsisa.dto;

public class AdminUserDTO {

	private Long id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String role;
	private int loyaltyPoints;
	private String userType;
	private int penalties;
	private boolean deleted;
	
	public AdminUserDTO() {
		
	}
	
	public AdminUserDTO(Long id, String name, String surname, String phoneNumber, String email, String role, int loyaltyPoints,
			String userType, int penalties, boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.loyaltyPoints = loyaltyPoints;
		this.userType = userType;
		this.penalties = penalties;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penals) {
		this.penalties = penals;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
