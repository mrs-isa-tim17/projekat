package com.project.mrsisa.domain;

import javax.persistence.*;

@Entity	
public class RegistrationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
	private User userRef;
	
	@Enumerated
	private RegistrationType registrationType;
	
	@Enumerated
	private ProcessingStatus status;
	
	
	public User getUserRef() {
		return userRef;
	}
	public void setUserRef(User user) {
		this.userRef = user;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	} 

	
	
}
