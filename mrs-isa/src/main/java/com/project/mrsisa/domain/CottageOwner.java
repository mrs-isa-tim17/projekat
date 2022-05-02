package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("CO")
public class CottageOwner extends User {

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cottage> cottages;
	
	@Column(name = "verificationCode", updatable = false)
	private String verificationCode;

	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public CottageOwner() {
		cottages = new ArrayList<Cottage>();
	}

	public List<Cottage> getCottages() {
		return Collections.unmodifiableList(cottages);
	}

	public void setCottages(List<Cottage> cottages) {
		this.cottages = cottages;
	}
	
	
}
