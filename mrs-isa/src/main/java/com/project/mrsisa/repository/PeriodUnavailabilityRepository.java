package com.project.mrsisa.repository;

import com.project.mrsisa.domain.PeriodUnavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PeriodUnavailabilityRepository extends JpaRepository<PeriodUnavailability, Long>{

    @Query(value="SELECT * FROM period_unavailability r WHERE r.offer_id=?1 and (r.start_date < ?2 or r.start_date < ?3) and r.end_date > CURRENT_DATE", nativeQuery = true)
    List<PeriodUnavailability> findCurrentPeriodsOfUnavailability(Long offer_id, LocalDateTime fromDate, LocalDateTime untilDate);

    @Transactional
    @Query(value="SELECT * FROM period_unavailability r WHERE r.offer_id=?1 order by start_date", nativeQuery = true)
	List<PeriodUnavailability> getListOfUnavailbilityForOffer(Long id);

}
