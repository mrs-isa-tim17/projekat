package com.project.mrsisa.dto;

import java.time.LocalDate;

public class ReservationForOwnerDTO {

	private Long reservationId;
	private Long clientId;
	private String clientName;
	private String clientSurname;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean quick;
	
	public ReservationForOwnerDTO() {
		
	}
	
	public ReservationForOwnerDTO(Long reservationId, Long clientId, String clientName, String clientSurname, LocalDate startDate, LocalDate endDate,
			boolean quick) {
		super();
		this.reservationId = reservationId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientSurname = clientSurname;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quick = quick;
	}
	
	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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

	public boolean isQuick() {
		return quick;
	}

	public void setQuick(boolean quick) {
		this.quick = quick;
	}
	
}
