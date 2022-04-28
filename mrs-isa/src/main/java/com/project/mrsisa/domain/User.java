package com.project.mrsisa.domain;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=STRING)
public abstract class User {
	@Id
	@SequenceGenerator(name = "userSeqGen", sequenceName = "userSeq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
	@Column(name="id", unique=true, nullable=false)
	protected Long id;
	
	@Column(name="name", nullable=false)
	protected String name;
	
	@Column(name = "surname", nullable=false)
	protected String surname;
	
	@Column(name = "email", unique = true, nullable=false)
	protected String email;
	
	@Column(name="password", nullable=false)
	protected String password;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
	protected Address address;
	
	@Column(name="phonenumber", nullable=false)
	protected String phoneNumber;
	
	@Column(name="deleted", nullable=false)
	protected boolean deleted;
	
	@Enumerated
	protected UserType userType;
	
	@Column(nullable=false)
	protected int loyaltyPoints;
	
	@OneToOne(mappedBy = "userRef", fetch = FetchType.LAZY)
	protected RegistrationRequest registrationRequest;
	
	@OneToOne(mappedBy = "userRef", fetch = FetchType.LAZY)
	protected DeleteRequest deleteRequest;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RegistrationRequest getRegistrationRequest() {
		return registrationRequest;
	}
	public void setRegistrationRequest(RegistrationRequest registrationRequest) {
		this.registrationRequest = registrationRequest;
	}
	public DeleteRequest getDeleteRequest() {
		return deleteRequest;
	}
	public void setDeleteRequest(DeleteRequest deleteRequest) {
		this.deleteRequest = deleteRequest;
	}
	
	

}
