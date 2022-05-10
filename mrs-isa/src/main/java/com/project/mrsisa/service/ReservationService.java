package com.project.mrsisa.service;

import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> getCottageHistoryReservation(Long id){
        return reservationRepository.findCottageReservationHistory(id, OfferType.COTTAGE.getValue());
    }

    public List<Reservation> getAdventureHistoryReservation(Long id){
        return reservationRepository.findCottageReservationHistory(id, OfferType.ADVENTURE.getValue());
    }

    public List<Reservation> getShipHistoryReservation(Long id){
        return reservationRepository.findCottageReservationHistory(id, OfferType.SHIP.getValue());
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation save(Reservation r){
        return reservationRepository.save(r);
    }
}
