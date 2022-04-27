package com.project.mrsisa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("FI")
public class FishingInstructor extends User {

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Adventure> adventures;

	public FishingInstructor() {
		adventures = new ArrayList<Adventure>();
	}

	public List<Adventure> getAdventures() {
		return Collections.unmodifiableList(adventures);
	}

	public void setAdventures(List<Adventure> adventures) {
		this.adventures = adventures;
	}
	
	

}
