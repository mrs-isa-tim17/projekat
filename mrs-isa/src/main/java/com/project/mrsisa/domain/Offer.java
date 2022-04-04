package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class Offer {
	private String name;
	private Address address;
	private String description;
	private boolean deleted;
	
	private List<Complaint> complaints;
	private List<ExperienceReview> experienceReviews;
	private List<AdditionalServices> additionalServices;
	private List<BehaviorRule> behaviorRules;
	private List<Image> images;
	private CancelCondition cancelCondition;
	private List<Pricelist> pricelists;
	private Calendar calendar;
	
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
	public List<Complaint> getComplaints() {
		return Collections.unmodifiableList(complaints);
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public List<ExperienceReview> getExperienceReviews() {
		return Collections.unmodifiableList(experienceReviews);
	}
	public void setExperienceReviews(List<ExperienceReview> experienceReviews) {
		this.experienceReviews = experienceReviews;
	}
	public List<AdditionalServices> getAdditionalServices() {
		return Collections.unmodifiableList(additionalServices);
	}
	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = additionalServices;
	}
	public List<BehaviorRule> getBehaviorRules() {
		return Collections.unmodifiableList(behaviorRules);
	}
	public void setBehaviorRules(List<BehaviorRule> behaviorRules) {
		this.behaviorRules = behaviorRules;
	}
	public List<Image> getImages() {
		return Collections.unmodifiableList(images);
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public CancelCondition getCancelCondition() {
		return cancelCondition;
	}
	public void setCancelCondition(CancelCondition cancelCondition) {
		this.cancelCondition = cancelCondition;
	}
	public List<Pricelist> getPricelists() {
		return Collections.unmodifiableList(pricelists);
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
