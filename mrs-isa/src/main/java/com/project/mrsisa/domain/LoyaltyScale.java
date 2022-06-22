package com.project.mrsisa.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class LoyaltyScale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated
	private UserType userType;
	
	@Column(nullable = false)
	private int discount;

	@Column(nullable = false)
	private int trashhold;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	private Role roles;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = true)
	private LocalDate endDate;
	
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getTrashhold() {
		return trashhold;
	}
	public void setTrashhold(int trashhold) {
		this.trashhold = trashhold;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Role getRoles() {
		return roles;
	}
	public void setRoles(Role roles) {
		this.roles = roles;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}
