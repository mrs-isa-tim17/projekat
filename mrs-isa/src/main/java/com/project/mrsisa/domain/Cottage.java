package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cottage extends Offer {
	@Column(nullable=false)
	private int roomQuantity;
	
	@Column(nullable=false)
	private int bedQuantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private CottageOwner owner;
	
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
	public CottageOwner getOwner() {
		return owner;
	}
	public void setOwner(CottageOwner owner) {
		this.owner = owner;
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cottage)){
			return false;
		}
		Cottage c = (Cottage) obj;
		if (id == c.getId())
			return true;
		return false;
	}
}
