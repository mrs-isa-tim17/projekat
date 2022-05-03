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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class AdditionalServices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToMany(mappedBy = "additionalServices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Offer> offer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saleAppointmentId")
	private SaleAppointment saleAppointment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservationId")
	private Reservation reservation;
	

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

	public SaleAppointment getSaleAppointment() {
		return saleAppointment;
	}

	public void setSaleAppointment(SaleAppointment saleAppointment) {
		this.saleAppointment = saleAppointment;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	

}
