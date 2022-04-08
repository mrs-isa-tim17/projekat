package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Adventure extends Offer {
	
	@Column(nullable=false)
	private String instructorBiography;
	
	@Column(nullable=false)
	private int capacity;
	
	@OneToMany(mappedBy="adventure", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FishingEquipment> fishingEquipments;

	public String getInstructorBiography() {
		return instructorBiography;
	}

	public void setInstructorBiography(String instructorBiography) {
		this.instructorBiography = instructorBiography;
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
