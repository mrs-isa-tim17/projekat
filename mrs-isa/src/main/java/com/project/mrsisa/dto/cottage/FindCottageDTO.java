package com.project.mrsisa.dto.cottage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.BehaviorRule;
import com.project.mrsisa.domain.CancelCondition;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.Image;
import com.project.mrsisa.domain.Pricelist;
import com.project.mrsisa.service.BehaviorRuleService;
import com.project.mrsisa.service.PricelistService;

import lombok.Data;

@Data
public class FindCottageDTO implements Serializable{
	    private int roomQuantity;
	    private String ownersEmail;
	    private String ownersName;
	    private String ownersSurname;
	    private int bedQuantity;
	    private Long id;
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
		private List<String> days;
		private String p1;
		private String p2;
		private String p3;
		private String p4;
		private double price;
		public PricelistService pricelistService;
		public BehaviorRuleService behaviorRuleService = new BehaviorRuleService();

	    public FindCottageDTO(Cottage cottage, List<BehaviorRule> rules, List<Image> imagesCottages, List<CancelCondition> conditions) {
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
	        
	        behavioralRules = new ArrayList<String>();
	        
	        for(BehaviorRule br : rules) {
	        	behavioralRules.add(br.getText());
	        }
	        
	        images = new ArrayList<String>();
	        for(Image i : imagesCottages) {
	        	images.add(i.getPath());
	        }
	        
	        cancelConditions = new ArrayList<String>();
	        for(CancelCondition cc : conditions) {
	        	this.days.add(String.valueOf(cc.getDays()));
				switch (cc.getDays()) {
				case 5:
					this.p1 = String.valueOf(cc.getPrecent());
					this.cancelConditions.add("Za otkazni rok od 0 - 5 dana pre početka događaja, naplaćuje se " + this.p1 + "od ukupne cene rezervacije.");
					break;
				case 10:
					this.p2 = String.valueOf(cc.getPrecent());
					this.cancelConditions.add("Za otkazni rok od 6 - 10 dana pre početka događaja, naplaćuje se " + this.p2 + "od ukupne cene rezervacije.");
					break;
				case 15:
					this.p3 = String.valueOf(cc.getPrecent());
					this.cancelConditions.add("Za otkazni rok od 11 - 15 dana pre početka događaja, naplaćuje se " + this.p3 + "od ukupne cene rezervacije.");
					break;
				case 20:
					this.p4 = String.valueOf(cc.getPrecent());
					this.cancelConditions.add("Za otkazni rok od 16 i više dana pre početka događaja, naplaćuje se " + this.p4 + "od ukupne cene rezervacije.");
					break;
				default:
					break;
				}
	        }
	        
	        
	        
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

		public String getOwnersName() {
			return ownersName;
		}

		public void setOwnersName(String ownersName) {
			this.ownersName = ownersName;
		}

		public String getOwnersSurname() {
			return ownersSurname;
		}

		public void setOwnersSurname(String ownersSurname) {
			this.ownersSurname = ownersSurname;
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

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public PricelistService getPricelistService() {
			return pricelistService;
		}

		public void setPricelistService(PricelistService pricelistService) {
			this.pricelistService = pricelistService;
		}

		public BehaviorRuleService getBehaviorRuleService() {
			return behaviorRuleService;
		}

		public void setBehaviorRuleService(BehaviorRuleService behaviorRuleService) {
			this.behaviorRuleService = behaviorRuleService;
		}
	    
	    
}
