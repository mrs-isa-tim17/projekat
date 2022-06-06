package com.project.mrsisa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.dto.PeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.mrsisa.domain.ShipOwner;
import com.project.mrsisa.repository.ShipOwnerRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipOwnerService {
	@Autowired
	private ShipOwnerRepository soRepository;
	@Autowired
	private ShipService shipService;
	@Autowired
	private ReservationService reservationService;

	public ShipOwner save(ShipOwner so) {
		return soRepository.save(so);
	}
	
	public List<ShipOwner> findAll(){
		return soRepository.findAll();
	}
	
	public ShipOwner findOne(Long id) {
		return soRepository.findById(id).orElseGet(null);
	}

	@Transactional
    public boolean checkIfFreeInPeriod(long id, PeriodDTO periodDTO) {
		Ship ship = shipService.findOne(id);
		System.out.println(ship.getId());
		ShipOwner shipOwner = findOne(ship.getOwner().getId());
		if (checkIfOwnerFreeInPeriod(shipOwner, periodDTO))
			return true;
		else
			return false;

    }

	private boolean checkIfOwnerFreeInPeriod(ShipOwner shipOwner, PeriodDTO periodDTO) {
		List<Ship> ships = shipService.getShipsByOwner(shipOwner);
		List<Reservation> reservations = new ArrayList<>();
		for (Ship s : ships){
			List<Reservation> res = reservationService.getReservationsForShipInPeriodWhenShipOwnerIsPresent(s, periodDTO.getFromDate(), periodDTO.getUntilDate());
			for (Reservation r : res){
				if (checkIfInterfere(r, periodDTO.getFromDate(), periodDTO.getUntilDate()))
					return false;
			}
		}
		return true;
	}

	private boolean checkIfInterfere(Reservation r, LocalDateTime fromDate, LocalDateTime untilDate) {
		if (r.getStartDateTime().compareTo(fromDate) >= 0)
			if (r.getEndDateTime().compareTo(untilDate) >= 0)
				if (r.getEndDateTime().compareTo(fromDate) <= 0)//nov
					return true;

		if (r.getStartDateTime().compareTo(fromDate) <= 0)
			if (r.getEndDateTime().compareTo(untilDate) <= 0)
				if (r.getStartDateTime().compareTo(untilDate) >= 0)//nov
					return true;

		if (r.getStartDateTime().compareTo(fromDate) <= 0)
			if (r.getEndDateTime().compareTo(untilDate) >= 0)
				return true;

		if (r.getStartDateTime().compareTo(fromDate) >= 0)
			if (r.getEndDateTime().compareTo(untilDate) <= 0)
				return true;

		return false;
	}
}
