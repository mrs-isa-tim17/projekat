package com.project.mrsisa.domain;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
public abstract class Offer {
	@Id
	@SequenceGenerator(name = "offerSeqGen", sequenceName = "offerSeq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerSeqGen")
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
	private Address address;
	
	@Column(nullable =false )
	private String description;
	
	@Column(nullable=false)
	private boolean deleted;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Complaint> complaints;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExperienceReview> experienceReviews;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AdditionalServices> additionalServices;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BehaviorRule> behaviorRules;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Image> images;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CancelCondition> cancelCondition;
	
	@OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pricelist> pricelists;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendarId", referencedColumnName = "id")
	private Calendar calendar;
	
	@ManyToMany
	@JoinTable(name = "subscriptions", joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
	private List<Client> subscribers;
	
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
	public List<CancelCondition> getCancelCondition() {
		return cancelCondition;
	}
	public void setCancelCondition(List<CancelCondition> cancelCondition) {
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
