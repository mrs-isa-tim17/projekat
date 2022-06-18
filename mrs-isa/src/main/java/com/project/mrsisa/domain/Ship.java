package com.project.mrsisa.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
public class Ship extends Offer  implements Serializable {
	private static final long serialVersionUID = 1L;
	
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

	@ManyToMany
	@JoinTable(name = "fishingEquipmentsShip", joinColumns=@JoinColumn(name = "ship_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "fishing_equipment_id", referencedColumnName = "id"))
	private List<FishingEquipment> fishingEquipments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private ShipOwner owner;
	
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public java.lang.String getEngineDesignation() {
		return engineDesignation;
	}

	public void setEngineDesignation(java.lang.String engineDesignation) {
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
		return fishingEquipments;
	}

	public void setFishingEquipments(List<FishingEquipment> fishingEquipments) {
		this.fishingEquipments = fishingEquipments;
	}

	public ShipOwner getOwner() {
		return owner;
	}

	public void setOwner(ShipOwner owner) {
		this.owner = owner;
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ship)){
			return false;
		}
		Ship c = (Ship) obj;
		if (id == c.getId())
			return true;
		return false;
	}
}
