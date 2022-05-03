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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class FishingEquipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipId", nullable = true)
	private Ship ship;
	
	@ManyToMany(mappedBy="fishingEquipments", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Adventure> adventure;
	
	public FishingEquipment() {
		this.adventure = new ArrayList<Adventure>();
	}
	
	public FishingEquipment(String name) {
		this.name = name;
		this.adventure = new ArrayList<Adventure>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Ship getShip() {
		return ship;
	}
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	public List<Adventure> getAdventure() {
		return adventure;
	}
	public void setAdventure(List<Adventure> adventure) {
		this.adventure = adventure;
	}
	
	

}
