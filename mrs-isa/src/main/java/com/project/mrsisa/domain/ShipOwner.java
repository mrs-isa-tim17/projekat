package com.project.mrsisa.domain;

import java.util.Collections;
import java.util.List;

public class ShipOwner extends User {
	
	private List<Ship> ships;

	public List<Ship> getShips() {
		return Collections.unmodifiableList(ships);
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}
	
	

}
