package com.project.mrsisa.dto;

public class AdminOfferDTO {
	private Long id;
	private String name;
	private String description;
	private String ownerName;
	private String ownerSurname;
	private String ownerEmail;
	private int capacity;
	private int totalNumberOfReservations;
	private double rate;
	
    private double longitude;
    private double latitude;
    
    private String type;
    
    private int bedQuantity;
    private int roomQuantity;
    private boolean deleted;
    
    
    public AdminOfferDTO() {
    	
    }

    // Za avanturu
    public AdminOfferDTO(Long id, String name, String description, String ownerName, String ownerSurname, String ownerEmail,
    		double longitude, double latitude, int capacity,  int totalNumberOfReservations, double rate, boolean deleted) {
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.ownerName = ownerName;
    	this.ownerSurname = ownerSurname;
    	this.ownerEmail = ownerEmail;
    	this.longitude = longitude;
    	this.latitude = latitude;
    	this.capacity = capacity;
    	this.totalNumberOfReservations = totalNumberOfReservations;
    	this.rate = rate;	
    	this.deleted = deleted;
    }
    
    
    
    // Za ship
    public AdminOfferDTO(Long id, String name, String description, String ownerName, String ownerSurname, String ownerEmail,
    		double longitude, double latitude, int capacity, String type, int totalNumberOfReservations, double rate, boolean deleted) {
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.ownerName = ownerName;
    	this.ownerSurname = ownerSurname;
    	this.ownerEmail = ownerEmail;
    	this.longitude = longitude;
    	this.latitude = latitude;
    	this.capacity = capacity;
    	this.type = type;
    	this.totalNumberOfReservations = totalNumberOfReservations;
    	this.rate = rate;	
    	this.deleted = deleted;
    }
    
    // Za cottage
    public AdminOfferDTO(Long id, String name, String description, String ownerName, String ownerSurname, String ownerEmail,
    		double longitude, double latitude, int bedQuantity, int roomQuantity, int totalNumberOfReservations, double rate, boolean deleted) {
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.ownerName = ownerName;
    	this.ownerSurname = ownerSurname;
    	this.ownerEmail = ownerEmail;
    	this.longitude = longitude;
    	this.latitude = latitude;
    	this.bedQuantity = bedQuantity;
    	this.roomQuantity = roomQuantity;
    	this.totalNumberOfReservations = totalNumberOfReservations;
    	this.rate = rate;	
    	this.deleted = deleted;

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


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getOwnerSurname() {
		return ownerSurname;
	}


	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}


	public String getOwnerEmail() {
		return ownerEmail;
	}


	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getTotalNumberOfReservations() {
		return totalNumberOfReservations;
	}


	public void setTotalNumberOfReservations(int totalNumberOfReservations) {
		this.totalNumberOfReservations = totalNumberOfReservations;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getBedQuantity() {
		return bedQuantity;
	}


	public void setBedQuantity(int bedQuantity) {
		this.bedQuantity = bedQuantity;
	}


	public int getRoomQuantity() {
		return roomQuantity;
	}


	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
    
    

}
