package com.project.mrsisa.service;

import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Reservation> getPastHistoryReservation(Long offer_id){
        return reservationRepository.findPastReservationHistory(offer_id);
    }

    public List<Reservation> getFutureHistoryReservation(Long offer_id){
        return reservationRepository.findFutureReservationHistory(offer_id);
    }

    public List<Reservation> getListOfReservationByOfferInInterval(long offerId, LocalDate fromDate, LocalDate untilDate){
        return reservationRepository.findCurrentReservationInInterval(offerId, fromDate, untilDate);
    }

	public List<Reservation> getAllReservationsForOffer(Long id) {
		return reservationRepository.findAllReservationsForOffer(id);
	}

    public List<Reservation> getCurrentReservationsForOffer(Long id) {
        return reservationRepository.findCurrentReservationsForOffer(id);
    }

    public List<Reservation> getReservationsForMonthAndYear(Long id,int month,int year){
        return reservationRepository.getAllReservationsForMonthAndYear(id,month,year);
    }

    public List<Reservation> getReservationForMonth(Long id){
        return reservationRepository.getAllReservationsForMonth(id);
    }

    public List<Reservation> getReservationForWeek(Long id){
        return reservationRepository.getAllReservationsForWeek(id);
    }

    public List<Reservation> getReservationForPeriod(Long id, String start,String end){
        System.out.println("id"+id+"datttt"+start);
        return reservationRepository.getAllReservationsForPeriod(id,start,end);
    }

}
