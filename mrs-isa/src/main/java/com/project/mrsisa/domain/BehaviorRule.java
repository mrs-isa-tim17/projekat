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
import javax.persistence.ManyToMany;

@Entity
public class BehaviorRule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String text;
	
	@ManyToMany (mappedBy = "behaviorRules", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Offer> offer;
	
	public BehaviorRule() {
		this.offer = new ArrayList<Offer>();
	}
	
	public BehaviorRule(String text) {
		this.text = text;
		this.offer = new ArrayList<Offer>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
