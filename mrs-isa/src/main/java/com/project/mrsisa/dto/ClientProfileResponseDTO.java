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
    private String city;
    private String country;
    private String address;
    private String serialNumber;
    
    
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
        this.penaltyNumber = client.getLoyaltyPoints();
        this.benefits = "Imate pravo za popust od " + discount;
        this.city = client.getAddress().getPlace().getPlaceName();
        this.country = client.getAddress().getPlace().getCountry();
        this.address = client.getAddress().getStreetName();
        this.serialNumber = client.getAddress().getSerialNumber();
    }

    public ClientProfileResponseDTO(String name, String surname, String email, String password, String phoneNumber, UserType userType, int loyaltyPoints, int penaltyNumber, String benefits, String city, String country, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.loyaltyPoints = loyaltyPoints;
        this.penaltyNumber = penaltyNumber;
        this.benefits = benefits;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
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
	

    
    public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
