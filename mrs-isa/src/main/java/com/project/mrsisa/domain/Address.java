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
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="latitude")
	private double latitude;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Place place;
	
	@OneToOne(mappedBy = "address")
	private Offer offer;
	
	@OneToOne(mappedBy = "address")
	private SaleAppointment saleAppointment;
	
	@OneToOne(mappedBy = "address")
	private User user;

	public Address(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Address() {
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
		this.latitude = latitude	;
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

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public SaleAppointment getSaleAppointment() {
		return saleAppointment;
	}

	public void setSaleAppointment(SaleAppointment saleAppointment) {
		this.saleAppointment = saleAppointment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
