package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("CL")
public class Client extends User {
	@Column(name="penaltyNumber", nullable=true)
	private int penaltyNumber;
	
	//@Column(name="isAuthenticated", nullable=true)
	//private boolean isAuthenticated;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ClientReview> clientReviews;
	
	@ManyToMany(mappedBy = "subscribers")
	private List<Offer> subscriptions;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> futureReservations;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> historyReservations;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Complaint> complaints;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExperienceReview> experienceReviews;

	@Column(name = "verificationCode", updatable = false)
	private String verificationCode;

	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Client() {
		clientReviews = new ArrayList<ClientReview>();
		subscriptions = new ArrayList<Offer>();
		futureReservations = new ArrayList<Reservation>();
		historyReservations = new ArrayList<Reservation>();
		complaints = new ArrayList<Complaint>();
		experienceReviews = new ArrayList<ExperienceReview>();
	}

	public int getPenaltyNumber() {
		return penaltyNumber;
	}
	public void setPenaltyNumber(int penaltyNumber) {
		this.penaltyNumber = penaltyNumber;
	}
	//public boolean isAuthenticated() {
	//	return isAuthenticated;
	//}
	//public void setAuthenticated(boolean isAuthenticated) {
		//this.isAuthenticated = isAuthenticated;
	//}
	public List<ClientReview> getClientReviews() {
		return Collections.unmodifiableList(clientReviews);
	}
	public void setClientReviews(List<ClientReview> clientReviews) {
		this.clientReviews = clientReviews;
	}
	public List<Offer> getSubscriptions() {
		return Collections.unmodifiableList(subscriptions);
	}
	public void setSubscriptions(List<Offer> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public List<Reservation> getFutureReservations() {
		return Collections.unmodifiableList(futureReservations);
	}
	public void setFutureReservations(List<Reservation> futureReservations) {
		this.futureReservations = futureReservations;
	}
	public List<Reservation> getHistoryReservations() {
		return Collections.unmodifiableList(historyReservations);
	}
	public void setHistoryReservations(List<Reservation> historyReservations) {
		this.historyReservations = historyReservations;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public List<ExperienceReview> getExperienceReviews() {
		return experienceReviews;
	}
	public void setExperienceReviews(List<ExperienceReview> experienceReviews) {
		this.experienceReviews = experienceReviews;
	}
	
	
	
}
