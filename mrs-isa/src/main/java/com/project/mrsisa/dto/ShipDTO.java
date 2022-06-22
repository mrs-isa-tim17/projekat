package com.project.mrsisa.dto;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShipDTO {
	private Long id;
	private String name;
	private Address address;
	private String description;
	private boolean deleted;
	private String type;
	private double length;
	private String engineDesignation;
	private double enginePower;
	private double maxSpeed;
	private String navigationEquipment;

	public void setNavigationEquipment(String navigationEquipment) {
		this.navigationEquipment = navigationEquipment;
	}

	private int capacity;
	private Long ownerId;

	private double price;
	private LocalDate priceStartDate;

	private Long priceListId;
	private List<String> additionalServices;

	private List<String> fishingEquipment;

	private double longitude;
	private double latitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	private List<String> behavioralRules;
	private List<String> images;
	private List<String> cancelConditions;
	private List<String> experienceReviews;
	private List<String> imagesPath;

	private List<Integer> days;

	private List<Double> percents;


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPriceStartDate() {
		return priceStartDate;
	}

	public void setPriceStartDate(LocalDate priceStartDate) {
		this.priceStartDate = priceStartDate;
	}

	public List<String> getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(List<String> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public List<String> getBehavioralRules() {
		return behavioralRules;
	}

	public void setBehavioralRules(List<String> behavioralRules) {
		this.behavioralRules = behavioralRules;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getCancelConditions() {
		return cancelConditions;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
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

	public List<String> getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(List<String> imagesPath) {
		this.imagesPath = imagesPath;
	}

	public List<Integer> getDays() {
		return days;
	}

	public String getNavigationEquipment() {
		return navigationEquipment;
	}

	public void setDays(List<Integer> days) {
		this.days = days;
	}

	public List<Double> getPercents() {
		return percents;
	}

	public void setPercents(List<Double> percents) {
		this.percents = percents;
	}

	public List<String> getFishingEquipment() {
		return fishingEquipment;
	}

	public void setFishingEquipment(List<String> fishingEquipment) {
		this.fishingEquipment = fishingEquipment;
	}

	private PricelistService pricelistService = new PricelistService();
	public ShipDTO() {
		super();
	}
	
	public ShipDTO(Ship ship,double newPrice) {
		id = ship.getId();
		name = ship.getName();
		address = ship.getAddress();
		description = ship.getDescription();
		deleted = ship.isDeleted();
		type = ship.getType();
		length = ship.getLength();
		engineDesignation = ship.getEngineDesignation();
		enginePower = ship.getEnginePower();
		maxSpeed = ship.getMaxSpeed();
		capacity = ship.getCapacity();
		List<AdditionalServices> addServices = ship.getAdditionalServices();
		additionalServices = new ArrayList<>();
		for(AdditionalServices as : addServices){
			additionalServices.add(as.getName());
		}

		List<BehaviorRule> behRules = ship.getBehaviorRules();
		System.out.println("ponasanjeee " + behRules.get(0).getText());
		behavioralRules = new ArrayList<>();
		for(BehaviorRule as : behRules){
			System.out.println("ponasanjeee " + as.getText());
			behavioralRules.add(as.getText());
		}

		List<FishingEquipment> fishEq = ship.getFishingEquipments();
		fishingEquipment = new ArrayList<>();
		System.out.println("FISHHHH " + fishEq.size());
		for(FishingEquipment as : fishEq){
			fishingEquipment.add(as.getName());
		}

		/*List<ExperienceReview> expReviews = ship.getExperienceReviews();
		experienceReviews = new ArrayList<>();
		for(ExperienceReview as : expReviews){
			experienceReviews.add(as.getText());
		}*/

		List<CancelCondition> cc = ship.getCancelCondition();
		days = new ArrayList<>();
		days.add(5);
		days.add(10);
		days.add(15);
		days.add(20);
		percents = new ArrayList<>();
		if(cc.size() == 0){
			percents.add(0.0);
			percents.add(0.0);
			percents.add(0.0);
			percents.add(0.0);
		}
		else {
			for (CancelCondition c : cc) {
				percents.add(c.getPrecent());
			}
		}
		System.out.print("idddd" + ship.getId());
		price = newPrice;

		latitude = ship.getAddress().getLatitude();
		longitude = ship.getAddress().getLongitude();



	}

	public ShipDTO(Ship ship) {
		id = ship.getId();
		name = ship.getName();
		address = ship.getAddress();
		description = ship.getDescription();
		deleted = ship.isDeleted();
		type = ship.getType();
		length = ship.getLength();
		engineDesignation = ship.getEngineDesignation();
		enginePower = ship.getEnginePower();
		maxSpeed = ship.getMaxSpeed();
		capacity = ship.getCapacity();
		List<AdditionalServices> addServices = ship.getAdditionalServices();
		additionalServices = new ArrayList<>();
		for(AdditionalServices as : addServices){
			additionalServices.add(as.getName());
		}

		List<BehaviorRule> behRules = ship.getBehaviorRules();
		behavioralRules = new ArrayList<>();
		for(BehaviorRule as : behRules){
			behavioralRules.add(as.getText());
		}

		List<FishingEquipment> fishEq = ship.getFishingEquipments();
		fishingEquipment = new ArrayList<>();
		for(FishingEquipment as : fishEq){
			fishingEquipment.add(as.getName());
		}

		/*List<ExperienceReview> expReviews = ship.getExperienceReviews();
		experienceReviews = new ArrayList<>();
		for(ExperienceReview as : expReviews){
			experienceReviews.add(as.getText());
		}*/

		List<CancelCondition> cc = ship.getCancelCondition();
		List<Integer> days = new ArrayList<>();
		days.add(5);
		days.add(10);
		days.add(15);
		days.add(20);
		percents = new ArrayList<>();
		for(CancelCondition c : cc){
			percents.add(c.getPrecent());
		}
		System.out.print("idddd" + ship.getId());


		latitude = ship.getAddress().getLatitude();
		longitude = ship.getAddress().getLongitude();



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

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}
