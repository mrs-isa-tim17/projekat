package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="streetName")
	private String streetName;
	
	@Column(name="serialNumber")
	private String serialNumber;
	

	@ManyToOne(fetch = FetchType.LAZY)

	private Place place;
	
	/*@OneToOne(mappedBy = "address")
	private Offer offer;*/
	
	/*@OneToOne(mappedBy = "address")
	private SaleAppointment saleAppointment;*/
	
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}*/

	/*public SaleAppointment getSaleAppointment() {
		return saleAppointment;
	}

	public void setSaleAppointment(SaleAppointment saleAppointment) {
		this.saleAppointment = saleAppointment;
	}*/

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
	
	
	
}
