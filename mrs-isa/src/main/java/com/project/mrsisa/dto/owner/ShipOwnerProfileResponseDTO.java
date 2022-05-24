package com.project.mrsisa.dto.owner;
import com.project.mrsisa.domain.ShipOwner;
import com.project.mrsisa.domain.UserType;

public class ShipOwnerProfileResponseDTO {
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
    
    
	public ShipOwnerProfileResponseDTO() {
		super();
	}

	public ShipOwnerProfileResponseDTO(ShipOwner shipOwner, int discount) {
    	this.name = shipOwner.getName();
        this.surname = shipOwner.getSurname();
        this.email = shipOwner.getEmail();
        this.password = "";
        this.phoneNumber = shipOwner.getPhoneNumber();
        this.userType = shipOwner.getUserType();
        this.loyaltyPoints = shipOwner.getLoyaltyPoints();
        this.penaltyNumber = shipOwner.getLoyaltyPoints();
        this.benefits = "Imate pravo na popust od " + discount;
        this.longitude = shipOwner.getAddress().getLongitude();
        this.latitude = shipOwner.getAddress().getLatitude();
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
