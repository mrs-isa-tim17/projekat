package com.project.mrsisa.dto.owner;

import com.project.mrsisa.domain.Admin;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.domain.FishingInstructor;
import com.project.mrsisa.domain.UserType;

public class CottageOwnerProfileResponseDTO {
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
    
    
	public CottageOwnerProfileResponseDTO() {
		super();
	}

	public CottageOwnerProfileResponseDTO(CottageOwner cottageOwner, int discount) {
    	this.name = cottageOwner.getName();
        this.surname = cottageOwner.getSurname();
        this.email = cottageOwner.getEmail();
        this.password = "";
        this.phoneNumber = cottageOwner.getPhoneNumber();
        this.userType = cottageOwner.getUserType();
        this.loyaltyPoints = cottageOwner.getLoyaltyPoints();
        this.penaltyNumber = cottageOwner.getLoyaltyPoints();
        this.benefits = "Imate pravo na popust od " + discount;
        this.longitude = cottageOwner.getAddress().getLongitude();
        this.latitude = cottageOwner.getAddress().getLatitude();
    }
	
	public CottageOwnerProfileResponseDTO(FishingInstructor fishInstructor, int discount) {
    	this.name = fishInstructor.getName();
        this.surname = fishInstructor.getSurname();
        this.email = fishInstructor.getEmail();
        this.password = "";
        this.phoneNumber = fishInstructor.getPhoneNumber();
        this.userType = fishInstructor.getUserType();
        this.loyaltyPoints = fishInstructor.getLoyaltyPoints();
        this.penaltyNumber = fishInstructor.getLoyaltyPoints();
        this.benefits = "Imate pravo na popust od " + discount;
        this.longitude = fishInstructor.getAddress().getLongitude();
        this.latitude = fishInstructor.getAddress().getLatitude();
    }
	
	public CottageOwnerProfileResponseDTO(Admin admin, int discount) {
    	this.name = admin.getName();
        this.surname = admin.getSurname();
        this.email = admin.getEmail();
        this.password = "";
        this.phoneNumber = admin.getPhoneNumber();
        this.userType = admin.getUserType();
        this.loyaltyPoints = admin.getLoyaltyPoints();
        this.penaltyNumber = admin.getLoyaltyPoints();
        this.benefits = "Imate pravo na popust od " + discount;
        this.longitude = admin.getAddress().getLongitude();
        this.latitude = admin.getAddress().getLatitude();
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
