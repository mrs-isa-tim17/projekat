package com.project.mrsisa.dto.cottage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.AdditionalServices;
import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.domain.Cottage;

import com.sun.mail.imap.protocol.INTERNALDATE;
import lombok.Data;

@Data
public class CreateUpdateCottageDTO {
	private int roomQuantity;
	private String ownersEmail;
	private Long ownerId;
	private int bedQuantity;
	private Long id;
	private String name;
	private String description;
	private boolean deleted;
	private double longitude;
    private double latitude;
    private double price;
	private LocalDate startDatePrice;
	private List<String> behavioralRules;
	private List<String> images;
	private List<String> cancelConditions;
	private List<String> experienceReviews;
	private List<String> additionalServices;
	private List<String> imagesPath;
	
	private List<Integer> days;
	private List<Double> percents;
	
	private Long priceListId;
    public List<String> getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(List<String> imagesPath) {
		this.imagesPath = imagesPath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getStartDatePrice() {
		return startDatePrice;
	}

	public void setStartDatePrice(LocalDate startDatePrice) {
		this.startDatePrice = startDatePrice;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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



	public CreateUpdateCottageDTO(Cottage cottage) {
		this.id = cottage.getId();
		this.roomQuantity=cottage.getRoomQuantity();
		//this.ownersEmail=cottage.getOwner().getEmail();
		this.bedQuantity=cottage.getBedQuantity();
		//this.addressSerialNumber=cottage.getAddress().getSerialNumber();
		this.name=cottage.getName();
		this.description=cottage.getDescription();
		this.deleted=cottage.isDeleted();
		this.longitude = cottage.getAddress().getLongitude();
		this.latitude = cottage.getAddress().getLatitude();
		List<AdditionalServices> addServices = cottage.getAdditionalServices();
		additionalServices = new ArrayList<>();
		for(AdditionalServices as : addServices){
			additionalServices.add(as.getName());
		}
		List<BehaviorRule> behRules = cottage.getBehaviorRules();
		System.out.println("ponasanjeee " + behRules.get(0).getText());
		behavioralRules = new ArrayList<>();
		for(BehaviorRule as : behRules){
			System.out.println("ponasanjeee " + as.getText());
			behavioralRules.add(as.getText());
		}
		List<CancelCondition> cc = cottage.getCancelCondition();
		days = new ArrayList<>();
		days.add(5);
		days.add(10);
		days.add(15);
		days.add(20);
		percents = new ArrayList<>();
		for(CancelCondition c : cc){
			percents.add(c.getPrecent());
		}

	}

	public CreateUpdateCottageDTO(Cottage cottage,double newPrice) {
		this.id = cottage.getId();
		this.roomQuantity=cottage.getRoomQuantity();
		//this.ownersEmail=cottage.getOwner().getEmail();
		this.bedQuantity=cottage.getBedQuantity();
		//this.addressSerialNumber=cottage.getAddress().getSerialNumber();
		this.name=cottage.getName();
		this.description=cottage.getDescription();
		this.deleted=cottage.isDeleted();
		this.longitude = cottage.getAddress().getLongitude();
		this.latitude = cottage.getAddress().getLatitude();
		List<AdditionalServices> addServices = cottage.getAdditionalServices();
		additionalServices = new ArrayList<>();
		for(AdditionalServices as : addServices){
			additionalServices.add(as.getName());
		}
		List<BehaviorRule> behRules = cottage.getBehaviorRules();
		System.out.println("ponasanjeee " + behRules.get(0).getText());
		behavioralRules = new ArrayList<>();
		for(BehaviorRule as : behRules){
			System.out.println("ponasanjeee " + as.getText());
			behavioralRules.add(as.getText());
		}
		List<CancelCondition> cc = cottage.getCancelCondition();
		days = new ArrayList<>();
		days.add(5);
		days.add(10);
		days.add(15);
		days.add(20);
		percents = new ArrayList<>();
		for(CancelCondition c : cc){
			percents.add(c.getPrecent());
		}
		price = newPrice;

	}

	public CreateUpdateCottageDTO(){

	}

	public int getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public String getOwnersEmail() {
		return ownersEmail;
	}

	public void setOwnersEmail(String ownersEmail) {
		this.ownersEmail = ownersEmail;
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

	public Long getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(Long priceListId) {
		this.priceListId = priceListId;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}
