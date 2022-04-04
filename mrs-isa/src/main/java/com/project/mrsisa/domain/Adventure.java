package com.project.mrsisa.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Adventure extends Offer {
	
	private String instructorBiography;
	private int capacity;
	
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
