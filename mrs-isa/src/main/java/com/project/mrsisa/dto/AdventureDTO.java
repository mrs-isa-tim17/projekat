package com.project.mrsisa.dto;

import java.util.List;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Address;
import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.Calendar;
import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Pricelist;

public class AdventureDTO {
	private Long id;
	private String name;	
	private Address address;
	private String description;
	private boolean deleted;
	private List<ExperienceReview> experienceReviews;
	private List<AdditionalServices> additionalServices;
	private List<BehaviorRule> behaviorRules;
	private List<Image> images;
	private List<CancelCondition> cancelCondition;
	private List<Pricelist> pricelists;	
	private Calendar calendar;
	
	public AdventureDTO() {
		super();
	}

	public AdventureDTO(Adventure adventure) {
		id = adventure.getId();
		name = adventure.getName();
		address = adventure.getAddress();
		description = adventure.getDescription();
		deleted = adventure.isDeleted();
		experienceReviews = adventure.getExperienceReviews();
		additionalServices = adventure.getAdditionalServices();
		behaviorRules = adventure.getBehaviorRules();
		images = adventure.getImages();
		cancelCondition = adventure.getCancelCondition();
		pricelists = adventure.getPricelists();
		calendar = adventure.getCalendar();	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public List<ExperienceReview> getExperienceReviews() {
		return experienceReviews;
	}
	public void setExperienceReviews(List<ExperienceReview> experienceReviews) {
		this.experienceReviews = experienceReviews;
	}
	public List<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}
	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public List<BehaviorRule> getBehaviorRules() {
		return behaviorRules;
	}
	public void setBehaviorRules(List<BehaviorRule> behaviorRules) {
		this.behaviorRules = behaviorRules;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public List<CancelCondition> getCancelCondition() {
		return cancelCondition;
	}
	public void setCancelCondition(List<CancelCondition> cancelCondition) {
		this.cancelCondition = cancelCondition;
	}
	public List<Pricelist> getPricelists() {
		return pricelists;
	}
	public void setPricelists(List<Pricelist> pricelists) {
		this.pricelists = pricelists;
	}
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	
}


