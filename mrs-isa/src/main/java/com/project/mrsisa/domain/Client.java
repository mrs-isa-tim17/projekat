package com.project.mrsisa.domain;

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
	@Column(name="penaltyNumber", nullable=false)
	private int penaltyNumber;
	
	@Column(name="isAuthenticated", nullable=false)
	private boolean isAuthenticated;
	
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
	
	public int getPenaltyNumber() {
		return penaltyNumber;
	}
	public void setPenaltyNumber(int penaltyNumber) {
		this.penaltyNumber = penaltyNumber;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
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
	
	
	
}