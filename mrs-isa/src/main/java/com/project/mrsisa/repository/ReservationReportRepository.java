package com.project.mrsisa.repository;

import com.project.mrsisa.domain.ReservationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationReportRepository extends JpaRepository<ReservationReport,Long> {

    @Query(value="SELECT * FROM reservation_report p WHERE p.reservation_id=?1", nativeQuery = true)
    ReservationReport haveReservationReport(Long id);
}
