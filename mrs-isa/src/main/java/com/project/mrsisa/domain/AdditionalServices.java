package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class AdditionalServices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToMany(mappedBy = "additionalServices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Offer> offer;

	@ManyToMany(mappedBy = "additionalServices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SaleAppointment> saleAppointment;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "reservationId")
	@ManyToMany(mappedBy = "additionalServices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservation;
	
	@Column(name="price", nullable=false)
	private double price;
	
	@Column(name="deleted", nullable=false)
	private boolean deleted;

	public AdditionalServices() {
		this.offer = new ArrayList<Offer>();
		
	}
	public AdditionalServices(String name) {
		this.name=name;
		this.offer = new ArrayList<Offer>();

	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Offer> getOffer() {
		return offer;
	}

	public void setOffer(List<Offer> offer) {
		this.offer = offer;
	}

	public List<SaleAppointment> getSaleAppointment() {
		return saleAppointment;
	}

	public void setSaleAppointment(List<SaleAppointment> saleAppointment) {
		this.saleAppointment = saleAppointment;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public AdditionalServices(String name, double price) {
		this.name=name;
		this.price = price;

	}
}
