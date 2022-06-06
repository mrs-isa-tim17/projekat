package com.project.mrsisa.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private LocalDateTime startDate;
	
	@Column(nullable=false)
	private LocalDateTime endDate;
	
	@Column(nullable=false)
	private double price;
	
	//@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "additionalServices_reservation", joinColumns=@JoinColumn(name = "reservation_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "additional_service_id", referencedColumnName = "id"))
	private List<AdditionalServices> additionalServices;

	@Column(nullable = false)
	private OfferType offerType;

	@Column
	private boolean reviewed;

	@Column(nullable=false)
	private boolean quick;

	@Column(nullable=false)
	private boolean canceled;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientId")
	private Client client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offerId")
	private Offer offer;

	@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Complaint> complaints;

	@Column(nullable = true)
	private boolean shipOwnerPresent;

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	public LocalDateTime getStartDateTime() {
		return startDate;
	}

	public void setStartDateTime(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDateTime() {
		return endDate;
	}

	public void setEndDateTime(LocalDateTime endDate) {
		this.endDate = endDate;
	}


	public LocalDate getStartDate() {
		return startDate.toLocalDate();
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate.atStartOfDay();
	}

	public LocalDate getEndDate() {
		return endDate.toLocalDate();
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate.atStartOfDay();
	}


	
	
	
	
	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<AdditionalServices> getAdditionalServices() {
		return Collections.unmodifiableList(additionalServices);
	}
	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public boolean isQuick() {
		return quick;
	}
	public void setQuick(boolean quick) {
		this.quick = quick;
	}
	public boolean isCanceled() {
		return canceled;
	}
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isShipOwnerPresent() {
		return shipOwnerPresent;
	}

	public void setShipOwnerPresent(boolean shipOwnerPresent) {
		this.shipOwnerPresent = shipOwnerPresent;
	}
}
