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
}
