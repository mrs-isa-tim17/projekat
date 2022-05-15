package com.project.mrsisa.domain;

import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private LocalDate startDate;
	
	@Column(nullable=false)
	private LocalDate endDate;
	
	@Column(nullable=false)
	private double price;
	
	@OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY)
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

	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
	
	
	
}
