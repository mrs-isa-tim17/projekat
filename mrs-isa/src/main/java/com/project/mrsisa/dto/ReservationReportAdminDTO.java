package com.project.mrsisa.dto;

import java.time.LocalDate;

public class ReservationReportAdminDTO {
	private Long id;
	private String clientName;
	private String clientSurname;
	private String clientEmail;
	private Boolean penalty;
	private String reportText;
	private String ownerName;
	private String ownerSurname;
	private String ownerEmail;
	private LocalDate startDate;
	private LocalDate endDate;
	private String offerName;
	
	public ReservationReportAdminDTO() {
		
	}
	
	public ReservationReportAdminDTO(Long id, String clientName, String clientSurname, String clientEmail,
			Boolean penalty, String reportText, String ownerName, String ownerSurname, String ownerEmail,
			LocalDate startDate, LocalDate endDate, String offerName) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.clientEmail = clientEmail;
		this.penalty = penalty;
		this.reportText = reportText;
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
		this.ownerEmail = ownerEmail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offerName = offerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public Boolean getPenalty() {
		return penalty;
	}

	public void setPenalty(Boolean penalty) {
		this.penalty = penalty;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}


	
}
