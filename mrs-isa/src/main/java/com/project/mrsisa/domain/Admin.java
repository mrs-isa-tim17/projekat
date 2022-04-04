package com.project.mrsisa.domain;

public class Admin extends User {
	private boolean initLoginChanged = false;

	public boolean isInitLoginChanged() {
		return initLoginChanged;
	}

	public void setInitLoginChanged(boolean initLoginChanged) {
		this.initLoginChanged = initLoginChanged;
	}
	
	
}
