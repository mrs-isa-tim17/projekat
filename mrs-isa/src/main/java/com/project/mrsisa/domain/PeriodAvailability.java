package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PeriodAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String startDate;
	
	@Column(nullable=false)
	private String endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offerId")
	private Offer offer;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Offer getCalendar() {
		return offer;
	}
	public void setCalendar(Offer offer) {
		this.offer = offer;
	}
	
	
	
}
