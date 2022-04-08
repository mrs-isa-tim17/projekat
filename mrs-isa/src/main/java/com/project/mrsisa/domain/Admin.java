package com.project.mrsisa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AD")
public class Admin extends User {
	@Column(name="initLoginChanged", nullable = true)
	private boolean initLoginChanged = false;

	public boolean isInitLoginChanged() {
		return initLoginChanged;
	}

	public void setInitLoginChanged(boolean initLoginChanged) {
		this.initLoginChanged = initLoginChanged;
	}
	
	
}
