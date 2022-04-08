package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("CO")
public class CottageOwner extends User {

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cottage> cottages;

	public List<Cottage> getCottages() {
		return Collections.unmodifiableList(cottages);
	}

	public void setCottages(List<Cottage> cottages) {
		this.cottages = cottages;
	}
	
	
}
