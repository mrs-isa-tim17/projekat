package com.project.mrsisa.dto.cottage;

import java.util.List;

import com.project.mrsisa.domain.Cottage;

import lombok.Data;

@Data
public class CreateUpdateCottageDTO {
	private int roomQuantity;
	private String ownersEmail;
	private int bedQuantity;
	private Long id;
	private String name;
	private String description;
	private boolean deleted;
	private double longitude;
    private double latitude;
    private double price;
	private List<String> behavioralRules;
	private List<String> images;
	private List<String> cancelConditions;
	private List<String> experienceReviews;
	private List<String> additionalServices;
	private List<String> imagesPath;
	
	private List<String> days;
	private String percent1;
	private String percent2;
	private String percent3;
	private String percent4;
	
	
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

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public String getPercent1() {
		return percent1;
	}

	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}

	public String getPercent2() {
		return percent2;
	}

	public void setPercent2(String percent2) {
		this.percent2 = percent2;
	}

	public String getPercent3() {
		return percent3;
	}

	public void setPercent3(String percent3) {
		this.percent3 = percent3;
	}

	public String getPercent4() {
		return percent4;
	}

	public void setPercent4(String percent4) {
		this.percent4 = percent4;
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
		this.roomQuantity=cottage.getRoomQuantity();
		//this.ownersEmail=cottage.getOwner().getEmail();
		this.bedQuantity=cottage.getBedQuantity();
		//this.addressSerialNumber=cottage.getAddress().getSerialNumber();
		this.name=cottage.getName();
		this.description=cottage.getDescription();
		this.deleted=cottage.isDeleted();
		this.id=cottage.getId();
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
	
	
}
