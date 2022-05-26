package com.project.mrsisa.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SaleAppointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private LocalDateTime startSaleDate;
	
	@Column(nullable=false)
	private double duration;
	
	@Column(nullable=false)
	private int peopleQuantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offerId")
	private Offer offer;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "additionalServices_sale_appointment", joinColumns=@JoinColumn(name = "sale_appointment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private List<AdditionalServices> additionalServices;
	
	@Column(nullable=false)
	private double discount;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;
	
	
	public LocalDateTime getStartSaleDate() {
		return startSaleDate;
	}
	public void setStartSaleDate(LocalDateTime startSaleDate) {
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
