package com.project.mrsisa.dto;

public class ComplaintDTO {
	private Long id;
	private String text;
	private String clientName;
	private String clientSurname;
	private String clientEmail;
	private String ownerName;
	private String ownerSurname;
	private String ownerEmail;
	private String offerName;
	private String startDate;
	private String endDate;
	
	public ComplaintDTO() {
		
	}
	
	public ComplaintDTO(Long id, String text, String clientName, String clientSurname, String clientEmail, String ownerName,
			String ownerSurname, String ownerEmail, String offerName, String startDate, String endDate) {
		super();
		this.id = id;
		this.text = text;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.clientEmail = clientEmail;
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
		this.ownerEmail = ownerEmail;
		this.offerName = offerName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
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

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
