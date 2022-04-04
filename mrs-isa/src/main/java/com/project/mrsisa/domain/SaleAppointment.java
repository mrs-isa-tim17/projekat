package com.project.mrsisa.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class SaleAppointment {
	private LocalDate startSaleDate;
	private double duration;
	private int peopleQuantity;
	private List<AdditionalServices> additionalServices;
	private double discount;
	private Address address;
	public LocalDate getStartSaleDate() {
		return startSaleDate;
	}
	public void setStartSaleDate(LocalDate startSaleDate) {
		this.startSaleDate = startSaleDate;
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
	public List<AdditionalServices> getAdditionalServices() {
		return Collections.unmodifiableList(additionalServices);
	}
	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
