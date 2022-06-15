package com.project.mrsisa.dto;

public class ExperienceReviewAdminDTO {
	private long id;
	private String clientName;
	private String clientSurname;
	private String clientEmail;
	private String ownerName;
	private String ownerSurname;
	private String ownerEmail;
	private double rate;
	private String offerType;
	private String text;
	
	public ExperienceReviewAdminDTO() {
		
	}
	
	public ExperienceReviewAdminDTO(long id, String clientName, String clientSurname, String ownerName,
			String ownerSurname, double rate, String offerType, String text, String clientEmail, String ownerEmail) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
		this.rate = rate;
		this.offerType = offerType;
		this.text = text;
		this.clientEmail = clientEmail;
		this.ownerEmail = ownerEmail;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientSurname() {
		return clientSurname;
	}
	public void setClientSurname(String clientSurname) {
		this.clientSurname = clientSurname;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerSurname() {
		return ownerSurname;
	}
	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	
}
