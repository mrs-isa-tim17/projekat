package com.project.mrsisa.domain;

public class ExperienceReview {
	
	private String text;
	private int rate;
	private Client client;
	private ProcessingStatus status;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ProcessingStatus getStatus() {
		return status;
	}
	public void setStatus(ProcessingStatus status) {
		this.status = status;
	}
	
	

}
