package com.project.mrsisa.repository;

import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Transactional
    @Query(value = "SELECT * FROM reservation r WHERE r.client_id = ?1 and r.offer_type = ?2 and r.start_date < CURRENT_DATE",
            nativeQuery = true)
    //@Query("SELECT rs FROM reservation r LEFT JOIN FETCH ")
    List<Reservation> findCottageReservationHistory(Long clientId, int offerType);

    @Transactional
    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and r.start_date < CURRENT_DATE", nativeQuery = true)
    List<Reservation> findPastReservationHistory(Long offer_id);

    @Transactional
    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and r.start_date > CURRENT_DATE",nativeQuery = true)
    List<Reservation> findFutureReservationHistory(Long offer_id);

    @Transactional
    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and (r.start_date < ?2 or r.start_date < ?3) and r.end_date > CURRENT_DATE", nativeQuery = true)
    List<Reservation> findCurrentReservationInInterval(long offerId, LocalDate fromDate, LocalDate untilDate);

    @Transactional
    @Query(value="SELECT * from reservation r WHERE r.offer_id=?1", nativeQuery = true)
    List<Reservation> findAllReservationsForAdventure(Long id);

}
