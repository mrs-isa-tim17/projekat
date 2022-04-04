package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class Client {
	
	private int penaltyNumber;
	private boolean isAuthenticated;
	private List<ClientReview> clientReviews;
	private List<Offer> subscriptions;
	private List<Reservation> futureReservations;
	private List<Reservation> historyReservations;
	
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
