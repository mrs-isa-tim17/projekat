package com.project.mrsisa.dto;


import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.domain.FishingEquipment;
import com.project.mrsisa.domain.Image;


public class AdventureDTO {
	private Long id;
	private String name;	
	private String streetName;
	private String country;
	private String city;
	private String serialNumber;
	private String description;
	private String instructorBiography;

	private List<String> behavioralRules;
	private List<String> images;
	private List<String> fishingEquipment;
	private List<String> cancelConditions;
	private List<String> experienceReviews;
	private List<String> additionalServices;
	
	private List<String> days;
	private List<String> percentage;

	private double price;
	private int capacity;
	
	
	public AdventureDTO() {
		this.behavioralRules = new ArrayList<String>();
		this.images =new ArrayList<String>();
		this.fishingEquipment=new ArrayList<String>();
		this.cancelConditions =new ArrayList<String>();
		this.experienceReviews = new ArrayList<String>();
		this.additionalServices = new ArrayList<String>();
		this.days = new ArrayList<String>();
		this.percentage = new ArrayList<String>();
	}

	public AdventureDTO(Adventure adventure, List<BehaviorRule> behavior, List<Image> images, List<FishingEquipment> fishingEquipments,
			List<CancelCondition> cancelConditions, List<ExperienceReview> experience, List<AdditionalServices> additionalServices , double price) {
		
		this.id = adventure.getId();
		this.name = adventure.getName();
		this.streetName = adventure.getAddress().getStreetName();
		this.country = adventure.getAddress().getPlace().getCountry();
		this.city = adventure.getAddress().getPlace().getPlaceName();
		this.serialNumber = adventure.getAddress().getSerialNumber();
		this.description = adventure.getDescription();
		this.instructorBiography = adventure.getInstructorBiography();
		
		this.capacity = adventure.getCapacity();
		this.price = price;
		
		this.behavioralRules = new ArrayList<String>();
		this.images =new ArrayList<String>();
		this.fishingEquipment=new ArrayList<String>();
		this.cancelConditions =new ArrayList<String>();
		this.experienceReviews = new ArrayList<String>();
		this.additionalServices = new ArrayList<String>();
		
		this.days = new ArrayList<String>();
		this.percentage = new ArrayList<String>();
		
		for(BehaviorRule br : behavior)
		{
			this.behavioralRules.add(br.getText());
		}
		for(Image im : images)
		{
			this.images.add(im.getPath());
		}
		for(FishingEquipment fe : fishingEquipments)
		{
			this.fishingEquipment.add(fe.getName());
		}
		for(CancelCondition cc : cancelConditions)
		{
			this.cancelConditions.add("Za otkazni rok od + " + String.valueOf(cc.getDays()) +" dana pre početka događaja, naplaćuje se " + String.valueOf(cc.getPrecent()) + "od ukupne cene rezervacije.");
			this.days.add(String.valueOf(cc.getDays()));
			this.percentage.add(String.valueOf(cc.getPrecent()));	
		}

		for(ExperienceReview er : experience)
		{ 
			experienceReviews.add(er.getText());	
		}
		
		for(AdditionalServices as : additionalServices)
		{
			this.additionalServices.add(as.getName());
		}	
	}
	
	
	public String getInstructorBiography() {
		return instructorBiography;
	}


	public void setInstructorBiography(String instructorBiography) {
		this.instructorBiography = instructorBiography;
	}


	public AdventureDTO( String name, String streetName, String country, String city, String description,
		boolean deleted, List<String> behavior, List<String> images, List<String> fishingEquipment,
		List<String> cancelConditions, List<String> experienceReviews, double price, int capacity) {
	super();
	this.name = name;
	this.streetName = streetName;
	this.country = country;
	this.city = city;
	this.description = description;
	this.behavioralRules = behavior;
	this.images = images;
	this.fishingEquipment = fishingEquipment;
	this.cancelConditions = cancelConditions;
	this.experienceReviews = experienceReviews;
	this.price = price;
	this.capacity = capacity;
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

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getBehaviorRules() {
		return behavioralRules;
	}

	public void setBehaviorRules(List<String> behavior) {
		this.behavioralRules = behavior;
	}

	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(List<String> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	public List<String> getCancelConditions() {
		return cancelConditions;
	}

	public void setCancelConditions(List<String> cancelConditions) {
		this.cancelConditions = cancelConditions;
	}

	public List<String> getExperienceReviews() {
		return experienceReviews;
	}

	public void setExperienceReviews(List<String> experienceReviews) {
		this.experienceReviews = experienceReviews;
	}

	public List<String> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<String> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	

	public List<String> getDays() {
		return days;
	}


	public void setDays(List<String> days) {
		this.days = days;
	}


	public List<String> getPercentage() {
		return percentage;
	}


	public void setPercentage(List<String> percentage) {
		this.percentage = percentage;
	}


	@Override
	public String toString() {
		return "AdventureDTO [id=" + id + ", name=" + name + ", streetName=" + streetName + ", country=" + country
				+ ", city=" + city + ", description=" + description + ", deleted=" + ", behaviorRules="
				+ behavioralRules + ", images=" + images + ", fishingEquipment=" + fishingEquipment
				+ ", cancelConditions=" + cancelConditions + ", experienceReviews=" + experienceReviews
				+ ", additionalServices=" + additionalServices + ", price=" + price + ", capacity=" + capacity + "]";
	}
	
	
}


