package com.project.mrsisa.domain;

public class LoyaltyScale {
	
	private UserType userType;
	private int discount;
	private int trashhold;
	
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getTrashhold() {
		return trashhold;
	}
	public void setTrashhold(int trashhold) {
		this.trashhold = trashhold;
	}
	
	
}
