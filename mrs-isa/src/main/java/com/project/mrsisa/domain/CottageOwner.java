package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class CottageOwner extends User {

	private List<Cottage> cottages;

	public List<Cottage> getCottages() {
		return Collections.unmodifiableList(cottages);
	}

	public void setCottages(List<Cottage> cottages) {
		this.cottages = cottages;
	}
	
	
}
