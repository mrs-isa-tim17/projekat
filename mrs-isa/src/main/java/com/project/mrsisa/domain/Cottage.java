package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cottage extends Offer {
	@Column(nullable=false)
	private int roomQuantity;
	
	@Column(nullable=false)
	private int bedQuantity;
	
	
	public int getRoomQuantity() {
		return roomQuantity;
	}
	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}
	public int getBedQuantity() {
		return bedQuantity;
	}
	public void setBedQuantity(int bedQuantity) {
		this.bedQuantity = bedQuantity;
	}
	
	
	
}
