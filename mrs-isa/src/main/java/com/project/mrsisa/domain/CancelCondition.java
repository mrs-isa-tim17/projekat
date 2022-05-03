package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CancelCondition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int days;
	
	@Column(nullable = false)
	private double precent;
	
	@ManyToMany(mappedBy = "cancelCondition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Offer> offer;
	
	public CancelCondition() {
		this.offer = new ArrayList<Offer>();
	}
	
	public CancelCondition(int days, double percentage) {
		this.days = days;
		this.precent = percentage;
		this.offer = new ArrayList<Offer>();
	}
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getPrecent() {
		return precent;
	}
	public void setPrecent(double precent) {
		this.precent = precent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Offer> getOffer() {
		return offer;
	}
	public void setOffer(List<Offer> offer) {
		this.offer = offer;
	}
	
	

}
