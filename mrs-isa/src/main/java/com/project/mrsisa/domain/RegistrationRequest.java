package com.project.mrsisa.domain;

public class RegistrationRequest {
	
	private User user;
	private RegistrationType registrationType;
	private ProcessingStatus status;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RegistrationType getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}
	public ProcessingStatus getStatus() {
		return status;
	}
	public void setStatus(ProcessingStatus status) {
		this.status = status;
	} 

	
	
}
