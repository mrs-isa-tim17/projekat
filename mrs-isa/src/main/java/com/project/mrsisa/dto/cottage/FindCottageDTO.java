package com.project.mrsisa.dto.cottage;

import java.io.Serializable;

import com.project.mrsisa.domain.Cottage;

import lombok.Data;

@Data
public class FindCottageDTO implements Serializable{
	 private int roomQuantity;
	    private String ownersEmail;
	    private String ownersName;
	    private String ownersSurname;
	    private int bedQuantity;
	    private Long id;
	    private String name;
	    private String addressSerialNumber;
	    private String addressStreetName;
	    private String addressCity;
	    private String addressCountry;
	    private String description;
	    private boolean deleted;

	    public FindCottageDTO(Cottage cottage) {
	        this.roomQuantity=cottage.getRoomQuantity();
	        this.ownersEmail=cottage.getOwner().getEmail();
	        this.ownersName=cottage.getOwner().getName();
	        this.ownersSurname=cottage.getOwner().getSurname();
	        this.bedQuantity=cottage.getBedQuantity();
	        this.addressSerialNumber=cottage.getAddress().getSerialNumber();
	        this.addressStreetName =cottage.getAddress().getStreetName();
	        this.addressCity = cottage.getAddress().getPlace().getPlaceName();
	        this.addressCountry = cottage.getAddress().getPlace().getCountry();
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

		public String getOwnersName() {
			return ownersName;
		}

		public void setOwnersName(String ownersName) {
			this.ownersName = ownersName;
		}

		public String getOwnersSurname() {
			return ownersSurname;
		}

		public void setOwnersSurname(String ownersSurname) {
			this.ownersSurname = ownersSurname;
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

		public String getAddressStreetName() {
			return addressStreetName;
		}

		public void setAddressStreetName(String addressStreetName) {
			this.addressStreetName = addressStreetName;
		}

		public String getAddressCity() {
			return addressCity;
		}

		public void setAddressCity(String addressCity) {
			this.addressCity = addressCity;
		}

		public String getAddressCountry() {
			return addressCountry;
		}

		public void setAddressCountry(String addressCountry) {
			this.addressCountry = addressCountry;
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
