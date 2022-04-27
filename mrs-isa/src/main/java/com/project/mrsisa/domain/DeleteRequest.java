package com.project.mrsisa.domain;

import javax.persistence.*;

@Entity
public class DeleteRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User userRef;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private ProcessingStatus status;
	
	public User getUserRef() {
		return userRef;
	}
	public void setUserRef(User user) {
		this.userRef = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
