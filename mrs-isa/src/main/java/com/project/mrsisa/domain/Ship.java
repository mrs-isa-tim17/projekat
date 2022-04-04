package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class Ship extends Offer {
	
	private String type;
	private double length;
	private String engineDesignation;
	private double enginePower;
	private double maxSpeed;
	private NavigationEquipment navigationEquipment;
	private int capacity;

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
