package com.project.mrsisa.dto.cottage;

import com.project.mrsisa.domain.Cottage;

import lombok.Data;

@Data
public class CreateUpdateCottageDTO {
	private int roomQuantity;
	private String ownersEmail;
	private int bedQuantity;
	private Long id;
	private String name;
	private String addressSerialNumber;
	private String description;
	private boolean deleted;

	public CreateUpdateCottageDTO(Cottage cottage) {
		this.roomQuantity=cottage.getRoomQuantity();
		this.ownersEmail=cottage.getOwner().getEmail();
		this.bedQuantity=cottage.getBedQuantity();
		this.addressSerialNumber=cottage.getAddress().getSerialNumber();
		this.name=cottage.getName();
		this.description=cottage.getDescription();
		this.deleted=cottage.isDeleted();
		this.id=cottage.getId();
	}

	public int getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public String getOwnersEmail() {
		return ownersEmail;
	}

	public void setOwnersEmail(String ownersEmail) {
		this.ownersEmail = ownersEmail;
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

	public String getAddressSerialNumber() {
		return addressSerialNumber;
	}

	public void setAddressSerialNumber(String addressSerialNumber) {
		this.addressSerialNumber = addressSerialNumber;
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
