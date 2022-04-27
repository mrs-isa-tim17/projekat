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
@DiscriminatorValue("SO")
public class ShipOwner extends User {

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ship> ships;

	public ShipOwner() {
		ships = new ArrayList<Ship>();
	}

	public List<Ship> getShips() {
		return Collections.unmodifiableList(ships);
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}
	
	

}
