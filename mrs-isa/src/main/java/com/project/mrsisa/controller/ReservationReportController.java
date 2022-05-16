package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.ReservationReport;
import com.project.mrsisa.dto.ReservationReportDTO;
import com.project.mrsisa.dto.ShipDTO;
import com.project.mrsisa.service.ClientService;
import com.project.mrsisa.service.ReservationReportService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservationReport", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationReportController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationReportService reservationReportService;

    @PostMapping(value="/save", consumes = "application/json")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public ResponseEntity<ReservationReportDTO> saveReservationReport(@RequestBody ReservationReportDTO reportDTO){
        ReservationReport report = new ReservationReport();
        report.setReportText(reportDTO.getReport());
        report.setReservationId(reportDTO.getReservationId());
        report.setClientId(reportDTO.getClientId());
        report.setUnarrivedClient(reportDTO.isUnarrivedClient());
        System.out.println(reportDTO.isSuggestedPenallty());
        report.setPenallty(reportDTO.isSuggestedPenallty());
        if(report.isUnarrivedClient()){
            Client client = clientService.findOne(report.getClientId());
            int penallty = client.getPenaltyNumber();
            client.setPenaltyNumber(penallty+1);
            clientService.save(client);
        }
        report = reservationReportService.save(report);
        return new ResponseEntity<>(new ReservationReportDTO(report), HttpStatus.CREATED);
    }
}
