package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class Calendar {
	private List<PeriodAvailability> periodAvailabilities;
	private List<PeriodUnavailability> periodUnavailabilities;
	private List<SaleAppointment> saleAppointments;
	private List<Reservation> reservations;
	
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
	
	
	
}
