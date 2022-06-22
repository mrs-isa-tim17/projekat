package com.project.mrsisa.dto;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.UserType;

public class ClientProfileResponseDTO {
	private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private UserType userType;
    private int loyaltyPoints;
    private int penaltyNumber;
    private String benefits;
    private double longitude;
    private double latitude;
    
    
	public ClientProfileResponseDTO() {
		super();
	}

	public ClientProfileResponseDTO(Client client, int discount) {
    	this.name = client.getName();
        this.surname = client.getSurname();
        this.email = client.getEmail();
        this.password = "";
        this.phoneNumber = client.getPhoneNumber();
        this.userType = client.getUserType();
        this.loyaltyPoints = client.getLoyaltyPoints();
        this.penaltyNumber = client.getPenaltyNumber();
        this.benefits = "Imate pravo za popust od " + discount;
        this.longitude = client.getAddress().getLongitude();
        this.latitude = client.getAddress().getLatitude();
    }


    public int getPenaltyNumber() {
        return penaltyNumber;
    }

    public void setPenaltyNumber(int penaltyNumber) {
        this.penaltyNumber = penaltyNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
