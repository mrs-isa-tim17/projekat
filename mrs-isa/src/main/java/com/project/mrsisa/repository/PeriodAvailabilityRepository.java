package com.project.mrsisa.repository;

import com.project.mrsisa.domain.PeriodAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PeriodAvailabilityRepository extends JpaRepository<PeriodAvailability, Long> {

    @Query(value="SELECT * FROM period_availability r WHERE r.offer_id=?1 and r.start_date < ?2 and r.end_date > CURRENT_DATE", nativeQuery = true)
    List<PeriodAvailability> findCurrentPeriodsOfAvailability(Long offer_id, LocalDateTime fromDate, LocalDateTime
            untilDate);


    @Transactional
    @Query(value="SELECT * FROM period_availability p WHERE p.offer_id=?1 order by start_date", nativeQuery = true)
    List<PeriodAvailability> getListOfAvailbilityForOffer(Long offer_id);

}
