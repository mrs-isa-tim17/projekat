package com.project.mrsisa.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SaleAppointmentDTO {
	private Long id;
	private Long offerId;
	private LocalDateTime startDateTime;
    private double longitude;
    private double latitude;
    private double duration;
    private int peopleQuantity;
	private List<String> additionalServices;
	private double price;
	
	
	public SaleAppointmentDTO() {
		
	}
	
	public SaleAppointmentDTO(Long id, Long offerId, LocalDateTime startDateTime, double longitude, double latitude,
			double duration, int peopleQuantity, List<String> additionalServices, double price) {
		super();
		this.id = id;
		this.offerId = offerId;
		this.startDateTime = startDateTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.duration = duration;
		this.peopleQuantity = peopleQuantity;
		this.additionalServices = additionalServices;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getPeopleQuantity() {
		return peopleQuantity;
	}

	public void setPeopleQuantity(int peopleQuantity) {
		this.peopleQuantity = peopleQuantity;
	}

	public List<String> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<String> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
