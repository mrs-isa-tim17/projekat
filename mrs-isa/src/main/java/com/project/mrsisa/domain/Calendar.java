package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PeriodAvailability> periodAvailabilities;
	
	@OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PeriodUnavailability> periodUnavailabilities;
	
	@OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SaleAppointment> saleAppointments;
	
	@OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations;
	
	@OneToOne(mappedBy = "calendar", fetch =FetchType.LAZY)
	private Offer offer;
	
	public List<PeriodAvailability> getPeriodAvailabilities() {
		return Collections.unmodifiableList(periodAvailabilities);
	}
	public void setPeriodAvailabilities(List<PeriodAvailability> periodAvailabilities) {
		this.periodAvailabilities = periodAvailabilities;
	}
	public List<PeriodUnavailability> getPeriodUnavailabilities() {
		return Collections.unmodifiableList(periodUnavailabilities);
	}
	public void setPeriodUnavailabilities(List<PeriodUnavailability> periodUnavailabilities) {
		this.periodUnavailabilities = periodUnavailabilities;
	}
	public List<SaleAppointment> getSaleAppointments() {
		return Collections.unmodifiableList(saleAppointments);
	}
	public void setSaleAppointments(List<SaleAppointment> saleAppointments) {
		this.saleAppointments = saleAppointments;
	}
	public List<Reservation> getReservations() {
		return Collections.unmodifiableList(reservations);
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
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
	
	
	
}
