package com.project.mrsisa.domain;

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
public class FishingEquipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
/*	@Column(nullable=false)
	private String units;
	
	@Column(nullable=false)
	private double quantity;
	*/
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipId", nullable = true)
	private Ship ship;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "fishingEquipmentsOffer", joinColumns=@JoinColumn(name = "fishing_equipment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "adventure_id", referencedColumnName = "id"))

	private List<Adventure> adventure;
	
	public FishingEquipment() {
		
	}
	
	public FishingEquipment(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}   */
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
