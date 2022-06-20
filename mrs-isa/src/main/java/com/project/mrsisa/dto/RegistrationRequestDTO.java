package com.project.mrsisa.dto;

public class RegistrationRequestDTO {
	
	private String id;
	private String name;
	private String surname;
	private String registrationType;
	private String email;
	private String registrationMessage;
	
	public RegistrationRequestDTO() {
		
	}
	
	public RegistrationRequestDTO(String name, String surname, String registrationType, String email, Long id, String registrationMessage) {
		super();
		this.id = id.toString();
		this.name = name;
		this.surname = surname;
		this.registrationType = registrationType;
		this.email = email;
		this.registrationMessage = registrationMessage;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistrationMessage() {
		return registrationMessage;
	}

	public void setRegistrationMessage(String registrationMessage) {
		this.registrationMessage = registrationMessage;
	}
	
	
}
