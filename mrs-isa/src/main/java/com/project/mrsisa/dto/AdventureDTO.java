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
import com.project.mrsisa.domain.Pricelist;


public class AdventureDTO {
	private Long id;
	private String name;	
	
    private double longitude;
    private double latitude;
    
	private String description;
	private String instructorBiography;

	private List<String> behavioralRules;
	private List<String> images;
	private List<String> fishingEquipment;
	private List<String> cancelConditions;
	private List<String> experienceReviews;
	private List<String> additionalServices;
	
	private List<String> days;
	private String p1;
	private String p2;
	private String p3;
	private String p4;


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
	}

	public AdventureDTO(Adventure adventure, List<BehaviorRule> behavior, List<Image> images, List<FishingEquipment> fishingEquipments,
			List<CancelCondition> cancelConditions, List<ExperienceReview> experience, List<AdditionalServices> additionalServices , double price) {
		
		this.id = adventure.getId();
		this.name = adventure.getName();
		this.latitude = adventure.getAddress().getLatitude();
		this.longitude = adventure.getAddress().getLongitude();
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
		//this.percentage = new ArrayList<String>();
		
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
			
			this.days.add(String.valueOf(cc.getDays()));
			switch (cc.getDays()) {
			case 5:
				this.p1 = String.valueOf(cc.getPrecent());
				this.cancelConditions.add("Za otkazni rok od 0 - 5 dana pre početka događaja, naplaćuje se " + this.p1 + "% od ukupne cene rezervacije.");
				break;
			case 10:
				this.p2 = String.valueOf(cc.getPrecent());
				this.cancelConditions.add("Za otkazni rok od 6 - 10 dana pre početka događaja, naplaćuje se " + this.p2 + "% od ukupne cene rezervacije.");
				break;
			case 15:
				this.p3 = String.valueOf(cc.getPrecent());
				this.cancelConditions.add("Za otkazni rok od 11 - 15 dana pre početka događaja, naplaćuje se " + this.p3 + "% od ukupne cene rezervacije.");
				break;
			case 20:
				this.p4 = String.valueOf(cc.getPrecent());
				this.cancelConditions.add("Za otkazni rok od 16 i više dana pre početka događaja, naplaćuje se " + this.p4 + "% od ukupne cene rezervacije.");
				break;
			default:
				break;
			}
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


	public AdventureDTO( String name, double latitude, double longitude, String description,
		boolean deleted, List<String> behavior, List<String> images, List<String> fishingEquipment,
		List<String> cancelConditions, List<String> experienceReviews, double price, int capacity) {
	super();
	this.name = name;
	this.latitude = latitude;
	this.longitude = longitude;
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
	


	
	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public List<BehaviorRule> getBehaviorRuleAdventure(){
		List<BehaviorRule> behavoirRules = new ArrayList<BehaviorRule>();
		for(String rule : this.behavioralRules)
		{
			BehaviorRule br = new BehaviorRule(rule);
			behavoirRules.add(br);
		}
		return behavoirRules;
	}
	
	public List<FishingEquipment> getFishingEquipmentAdventure(){
		List<FishingEquipment> fishingEquipmentAdventure = new ArrayList<FishingEquipment>();
		for(String equipmentName : this.fishingEquipment) 
		{
			FishingEquipment fe = new FishingEquipment(equipmentName);
			fishingEquipmentAdventure.add(fe);
			System.out.println("PRINT: " + fe.getName());
		}
		return fishingEquipmentAdventure;
	}
	
	public List<CancelCondition> getCancelConditionsAdventure(){
		List<CancelCondition> cancelConditionsAdventure = new ArrayList<CancelCondition>();
		for(int i = 0; i < this.days.size(); i++) 
		{
			CancelCondition cc;
			switch (Integer.parseInt(this.days.get(i))) {
			case 5:
				cc = new CancelCondition(5, Double.parseDouble(this.p1));
				cancelConditionsAdventure.add(cc);
				break;
			case 10:
				cc = new CancelCondition(5, Double.parseDouble(this.p2));
				cancelConditionsAdventure.add(cc);
				break;
			case 15:
				cc = new CancelCondition(5, Double.parseDouble(this.p3));
				cancelConditionsAdventure.add(cc);
				break;
			case 20:
				cc = new CancelCondition(5, Double.parseDouble(this.p4));
				cancelConditionsAdventure.add(cc);
				break;
			default:
				break;
			}
		}
		return cancelConditionsAdventure;
	}
	public List<Pricelist> getPricelistAdventure(){
		Pricelist pricelist = new Pricelist(this.price);
		ArrayList<Pricelist> pricelists = new ArrayList<Pricelist>();
		pricelists.add(pricelist);
		return pricelists;
		}
	
	public List<AdditionalServices> getAdditionalServicesAdventure(){
		List<AdditionalServices> additionalServicesAdventure = new ArrayList<AdditionalServices>();
		
		for(String service : this.additionalServices) 
		{
			AdditionalServices as = new AdditionalServices(service);
			additionalServicesAdventure.add(as);	
		}
		return additionalServicesAdventure;
	}
	
	public List<Image> getImagesAdventure(){
		return new ArrayList<Image>();
	}
}


