package com.project.mrsisa.dto;

public class AdminDeletingRequestDTO {

	private Long id;
	private String userName;
	private String userSurname;
	private String text;
	private String userType;
	private String role;
	
	
	public AdminDeletingRequestDTO() {
		
	}


	public AdminDeletingRequestDTO(Long id, String userName, String userSurname, String text, String userType, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.userSurname = userSurname;
		this.text = text;
		this.userType = userType;
		this.role = role;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
