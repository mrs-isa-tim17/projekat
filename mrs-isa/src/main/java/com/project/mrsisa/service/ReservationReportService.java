package com.project.mrsisa.service;

import com.project.mrsisa.domain.ReservationReport;
import com.project.mrsisa.repository.ReservationReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationReportService {
    @Autowired
    ReservationReportRepository reservationReportRepository;

    public ReservationReport save(ReservationReport rr){
        return reservationReportRepository.save(rr);
    }
}
