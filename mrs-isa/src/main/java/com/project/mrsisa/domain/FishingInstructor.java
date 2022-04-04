package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class FishingInstructor extends User {
	
	private List<Adventure> adventures;

	public List<Adventure> getAdventures() {
		return Collections.unmodifiableList(adventures);
	}

	public void setAdventures(List<Adventure> adventures) {
		this.adventures = adventures;
	}
	
	

}
