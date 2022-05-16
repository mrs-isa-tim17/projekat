package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PeriodAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private LocalDateTime startDate;
	
	@Column(nullable=false)
	private LocalDateTime endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offerId")
	private Offer offer;
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
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
