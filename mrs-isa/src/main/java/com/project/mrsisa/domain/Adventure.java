package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Adventure extends Offer {
	
	@Column(nullable=false)
	private String instructorBiography;
	
	@Column(nullable=false)
	private int capacity;
	
	@ManyToMany
	@JoinTable(name = "fishingEquipmentsOffer", joinColumns=@JoinColumn(name = "adventure_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "fishing_equipment_id", referencedColumnName = "id"))
	private List<FishingEquipment> fishingEquipments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private FishingInstructor owner;
	
	public Adventure() {
		super();
		this.fishingEquipments = new ArrayList<FishingEquipment>();
	}
	
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

	public FishingInstructor getOwner() {
		return owner;
	}

	public void setOwner(FishingInstructor owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Adventure [instructorBiography=" + instructorBiography + ", capacity=" + capacity
				+ ", fishingEquipments=" + fishingEquipments + ", owner=" + owner + ", id=" + id + ", name=" + name
				+ ", address=" + address + ", description=" + description + ", deleted=" + deleted + ", complaints="
				+ complaints + ", experienceReviews=" + experienceReviews + ", additionalServices=" + additionalServices
				+ ", behaviorRules=" + behaviorRules + ", cancelCondition=" + cancelCondition + ", pricelists="
				+ pricelists + ", subscribers=" + client + ", getInstructorBiography()="
				+ getInstructorBiography() + ", getCapacity()=" + getCapacity() + ", getFishingEquipments()="
				+ getFishingEquipments() + ", getOwner()=" + getOwner() + ", getName()=" + getName() + ", getAddress()="
				+ getAddress() + ", getDescription()=" + getDescription() + ", isDeleted()=" + isDeleted()
				+ ", getComplaints()=" + getComplaints() + ", getExperienceReviews()=" + getExperienceReviews()
				+ ", getAdditionalServices()=" + getAdditionalServices() + ", getBehaviorRules()=" + getBehaviorRules()
				+ ", getImages()=" + getImages() + ", getCancelCondition()=" + getCancelCondition()
				+ ", getSubscribers()=" + getClient() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cottage)){
			return false;
		}
		Adventure c = (Adventure) obj;
		if (id == c.getId())
			return true;
		return false;
	}
}
