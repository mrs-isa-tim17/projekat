package com.project.mrsisa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationForOwnerDTO {

	private Long id;
	private Long clientId;
	private String clientName;
	private String clientSurname;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private boolean quick;
	private String  offerName;
	
	public ReservationForOwnerDTO() {
		
	}
	
	public ReservationForOwnerDTO(Long reservationId, Long clientId, String clientName, String clientSurname, LocalDateTime startDate, LocalDateTime endDate,
			boolean quick, String name) {
		super();
		this.id = reservationId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quick = quick;
		this.offerName = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long reservationId) {
		this.id = reservationId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isQuick() {
		return quick;
	}

	public void setQuick(boolean quick) {
		this.quick = quick;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	
}
