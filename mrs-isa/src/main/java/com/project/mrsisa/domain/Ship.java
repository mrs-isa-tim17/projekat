package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Ship extends Offer {
	
	@Column(nullable=false)
	private String type;
	
	@Column(nullable=false)
	private double length;
	
	@Column(nullable=false)
	private String engineDesignation;	
	
	@Column(nullable=false)
	private double enginePower;
	
	@Column(nullable=false)
	private double maxSpeed;
	
	@Enumerated
	private NavigationEquipment navigationEquipment;

	@Column(nullable=false)
	private int capacity;

	@OneToMany(mappedBy = "ship", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FishingEquipment> fishingEquipments;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getEngineDesignation() {
		return engineDesignation;
	}

	public void setEngineDesignation(String engineDesignation) {
		this.engineDesignation = engineDesignation;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public NavigationEquipment getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setNavigationEquipment(NavigationEquipment navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<FishingEquipment> getFishingEquipments() {
		return Collections.unmodifiableList(fishingEquipments);
	}

	public void setFishingEquipments(List<FishingEquipment> fishingEquipments) {
		this.fishingEquipments = fishingEquipments;
	}
	
	
}
