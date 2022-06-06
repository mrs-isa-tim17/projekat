package com.project.mrsisa.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class LoyaltyPoints {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private double points;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = true)
	private LocalDate endDate;
	
	@Enumerated
	private UserTypeLoyaltyPoints userTypeLoyaltyPoints;
	
	
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserTypeLoyaltyPoints getUserTypeLoyaltyPoints() {
		return userTypeLoyaltyPoints;
	}
	public void setUserTypeLoyaltyPoints(UserTypeLoyaltyPoints userTypeLoyaltyPoints) {
		this.userTypeLoyaltyPoints = userTypeLoyaltyPoints;
	}
	
	

}
