package com.project.mrsisa.dto;

import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;

public class CottageDTO {
	
	private int roomQuantity;
	private Long ownerId;
	private int bedQuantity;
	private Long id;
	private String name;
	private Address address;
	private String description;
	private boolean deleted;
	
	
	public CottageDTO() {
		super();
	}
	
	public CottageDTO(Cottage cottage) {
		roomQuantity = cottage.getRoomQuantity();
		ownerId = cottage.getOwner().getId();
		bedQuantity = cottage.getBedQuantity();
		id = cottage.getId();
		name = cottage.getName();
		address = cottage.getAddress();
		description = cottage.getDescription();
		deleted = cottage.isDeleted();
	}
	
	public int getRoomQuantity() {
		return roomQuantity;
	}
	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwner(Long ownerId) {
		this.ownerId = ownerId;
	}
	public int getBedQuantity() {
		return bedQuantity;
	}
	public void setBedQuantity(int bedQuantity) {
		this.bedQuantity = bedQuantity;
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
	
	
