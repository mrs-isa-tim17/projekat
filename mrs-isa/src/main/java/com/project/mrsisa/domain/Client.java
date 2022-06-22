package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CL")
public class Client extends User {
	@Column(name="penaltyNumber", nullable=true)
	private int penaltyNumber;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "subscriptions", inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"), joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
	private List<Offer> subscriptions;//client je vlasnik veze
	
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
