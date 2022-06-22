package com.project.mrsisa.dto.cottage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.*;
import com.project.mrsisa.domain.NavigationEquipment;
import com.project.mrsisa.service.BehaviorRuleService;
import com.project.mrsisa.service.PricelistService;

import lombok.Data;

@Data
public class FindCottageDTO implements Serializable{
	    private int roomQuantity;
	    private int bedQuantity;
	    private Long id;
		private Long priceListId;
	    private String name;
	    private double longitude;
	    private double latitude;
	    private String description;
	    private boolean deleted;
	    private List<String> behavioralRules;
		private List<String> images;
		private List<String> cancelConditions;
		private List<String> experienceReviews;
		private List<String> additionalServices;

		private List<Integer> days;
		private List<Double> percents;
		private double price;

		private LocalDate priceStartDate;
		public PricelistService pricelistService;
		public BehaviorRuleService behaviorRuleService = new BehaviorRuleService();

	    public FindCottageDTO(Cottage cottage, List<BehaviorRule> rules, List<Image> imagesCottages, List<CancelCondition> conditions, List<AdditionalServices> addServices, Pricelist pricelist) {
	        this.roomQuantity=cottage.getRoomQuantity();
	        //this.ownersEmail=cottage.getOwner().getEmail();
	       // this.ownersName=cottage.getOwner().getName();
	        //this.ownersSurname=cottage.getOwner().getSurname();
	        this.bedQuantity=cottage.getBedQuantity();
	        this.longitude = cottage.getAddress().getLongitude();
	        this.latitude = cottage.getAddress().getLatitude();
	        this.name=cottage.getName();
	        this.description=cottage.getDescription();
	        this.deleted=cottage.isDeleted();
	        this.id=cottage.getId();

			this.priceListId = pricelist.getId();
	        
	        behavioralRules = new ArrayList<String>();
	        
	        for(BehaviorRule br : rules) {
	        	behavioralRules.add(br.getText());
	        }
	        
	        images = new ArrayList<String>();
	        for(Image i : imagesCottages) {
	        	images.add(i.getPath());
	        }
	        
	       	days = new ArrayList<Integer>();
			percents = new ArrayList<Double>();
	        for(CancelCondition cc : conditions) {

	        	days.add(cc.getDays());
				percents.add(cc.getPrecent());

				}
			System.out.println(this.days);
			System.out.println(this.percents);


			additionalServices = new ArrayList<String>();
			for(AdditionalServices as : addServices){
				this.additionalServices.add(as.getName());
			}

			this.price = pricelist.getPrice();
			this.priceStartDate = pricelist.getStartDate();
	    }

	public int getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public int getBedQuantity() {
		return bedQuantity;
	}

	public void setBedQuantity(int bedQuantity) {
		this.bedQuantity = bedQuantity;
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

	public List<Integer> getDays() {
		return days;
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

	public double getPrice() {
		return price;
	}

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
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
}
