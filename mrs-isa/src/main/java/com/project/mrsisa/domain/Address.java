package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="streetName")
	private String streetName;
	
	@Column(name="serialNumber")
	private String serialNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Place place;
	
	@OneToOne(mappedBy = "address")
	private Offer offer;
	
	@OneToOne(mappedBy = "address")
	private SaleAppointment saleAppointment;
	
	@OneToOne(mappedBy = "address")
	private User user;
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Place getPlace() {
		return place;
	}
	
	public void setPlace(Place place) {
		this.place = place;
	}
	
	
	
}