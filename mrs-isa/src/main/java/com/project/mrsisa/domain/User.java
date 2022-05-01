package com.project.mrsisa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=STRING)
public class User implements UserDetails {
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
	

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//?????????
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

	@JsonIgnore
	@OneToOne(mappedBy = "userRef", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	protected RegistrationRequest registrationRequest;

	@JsonIgnore
	@OneToOne(mappedBy = "userRef", fetch = FetchType.LAZY)
	protected DeleteRequest deleteRequest;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	private Role roles;

	@Column(name = "last_password_reset_date")
	private Timestamp lastPasswordResetDate;

	@Column(name= "enabled")
	private boolean enabled;

	public void setRoleId(long id) {roles = new Role(); roles.setId(id);}
	public Long getRoleId() { return roles.getId();}
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
		Timestamp now = new Timestamp(new Date().getTime());
		this.setLastPasswordResetDate(now);
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
	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	public void setRoles(Role roles) {this.roles = roles;}

	public Role getRoles() {
		return roles;
	}

	public DeleteRequest getDeleteRequest() {
		return deleteRequest;
	}
	public void setDeleteRequest(DeleteRequest deleteRequest) {
		this.deleteRequest = deleteRequest;
	}


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();
		ga.add(this.roles);
		return ga;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	public void setUsername(String emailAsUsername) { setEmail(emailAsUsername); }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}
