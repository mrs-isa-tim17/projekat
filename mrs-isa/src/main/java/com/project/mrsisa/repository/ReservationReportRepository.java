package com.project.mrsisa.repository;

import com.project.mrsisa.domain.ReservationReport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationReportRepository extends JpaRepository<ReservationReport,Long> {

	@Query(value="SELECT * from reservation_report r WHERE r.status=0", nativeQuery = true)
	public List<ReservationReport> findUnprocessedReport();
	
	public ReservationReport findOneById(Long id);
	
}
