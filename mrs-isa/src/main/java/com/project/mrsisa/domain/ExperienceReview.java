package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExperienceReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =false)
	private String text;

	@Column(nullable =false)
	private int rate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId")
	private Client client;
	
	@Enumerated
	private ProcessingStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "offerId")
	private Offer offer;
	
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
	/*public Client getClient() {
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
	*/
	

}
