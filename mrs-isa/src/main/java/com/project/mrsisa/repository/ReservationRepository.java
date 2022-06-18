package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation r WHERE r.client_id = ?1 and r.offer_type = ?2 and r.start_date < CURRENT_DATE",
            nativeQuery = true)
    //@Query("SELECT rs FROM reservation r LEFT JOIN FETCH ")
    List<Reservation> findCottageReservationHistory(Long clientId, int offerType);

    @Transactional
    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and r.start_date < CURRENT_DATE and r.client_id is not null", nativeQuery = true)
    List<Reservation> findPastReservationHistory(Long offer_id);

    @Transactional
    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and r.start_date > CURRENT_DATE and r.client_id is not null",nativeQuery = true)
    List<Reservation> findFutureReservationHistory(Long offer_id);

    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and (r.start_date < ?2 or r.start_date < ?3) and r.end_date > CURRENT_DATE and not canceled", nativeQuery = true)
    List<Reservation> findCurrentReservationInInterval(long offerId, LocalDateTime fromDate, LocalDateTime untilDate);

    @Transactional
    @Query(value="SELECT * from reservation r WHERE r.offer_id=?1 and not canceled order by r.start_date", nativeQuery = true)
    List<Reservation> findAllReservationsForOffer(Long id);

    @Transactional
    @Query(value="select * from reservation r where r.offer_id = ?1 and DATE(NOW()) between r.start_date and r.end_date and client_id is not null;", nativeQuery = true)
    List<Reservation> findCurrentReservationsForOffer(Long id);

    @Transactional
    @Query(value="select * from reservation r where r.offer_id = ?1 and EXTRACT(MONTH FROM r.start_date) = ?2 and EXTRACT(YEAR FROM r.start_date) = ?3",nativeQuery = true)
    List<Reservation> getAllReservationsForMonthAndYear(Long id,int month, int year);

    @Transactional
    @Query(value="select * from reservation r where r.offer_id=?1 and r.start_date between DATE(NOW())-interval '1 month' and DATE(NOW());",nativeQuery = true)
    List<Reservation> getAllReservationsForMonth(Long id);

    @Transactional
    @Query(value="select * from reservation r where r.offer_id=?1 and r.start_date between DATE(NOW())-interval '7 days' and DATE(NOW());",nativeQuery = true)
    List<Reservation> getAllReservationsForWeek(Long id);

    @Transactional
    @Query(value="select * from reservation r where r.offer_id=?1 and r.start_date between CAST(?2 AS TIMESTAMP) and CAST(?3 AS TIMESTAMP)",nativeQuery = true)
    List<Reservation> getAllReservationsForPeriod(Long id,String start, String end);

    @Query(value="SELECT * FROM reservation r WHERE r.offer_id=?1 and (r.start_date < ?2 or r.start_date < ?3) and r.end_date > CURRENT_DATE and r.ship_owner_present", nativeQuery = true)
    List<Reservation> getReservationsForShipInPeriodWhenShipOwnerIsPresent(Long id, LocalDateTime fromDate, LocalDateTime untilDate);

    @Query(value="SELECT * FROM reservation r WHERE r.client_id=?1 and r.start_date = ?3 and r.end_date = ?4 and r.offer_id = ?2", nativeQuery = true)
    Reservation checkIfClientCanceledReservationWithSameParametars(Long id, long offerId, LocalDateTime fromDate, LocalDateTime untilDate);

    @Query(value="SELECT * FROM reservation r WHERE r.client_id=?1 and r.start_date > CURRENT_DATE", nativeQuery = true)
    List<Reservation> getUpcomingReservationsForClient(long id);
    
    @Query(value="SELECT * from reservation r WHERE r.start_date>=?1 and r.end_date<=?2", nativeQuery = true)
    List<Reservation> getReservationsForPeriod(LocalDate startdate, LocalDate endDate);
    
    @Transactional
    @Query(value="SELECT * from reservation r WHERE r.offer_id=?1 and not canceled and quick is true order by r.start_date", nativeQuery = true)
    List<Reservation> findAllQuickReservationsForOffer(Long id);
    
    @Transactional
    @Query(value="SELECT * from reservation r WHERE r.offer_id=?1 and not canceled and quick is false order by r.start_date", nativeQuery = true)
    List<Reservation> findAllOrdinaryReservationsForOffer(Long id);
}
