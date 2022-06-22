package com.project.mrsisa.controller;

import com.project.mrsisa.domain.Adventure;
import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.OfferType;
import com.project.mrsisa.domain.ProcessingStatus;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.domain.ReservationReport;
import com.project.mrsisa.domain.Ship;
import com.project.mrsisa.domain.User;
import com.project.mrsisa.dto.ReservationReportAdminDTO;
import com.project.mrsisa.dto.ReservationReportDTO;
import com.project.mrsisa.dto.ShipDTO;
import com.project.mrsisa.dto.TextDTO;
import com.project.mrsisa.service.AdventureService;
import com.project.mrsisa.service.ClientService;
import com.project.mrsisa.service.CottageService;
import com.project.mrsisa.service.ReservationReportService;
import com.project.mrsisa.service.ReservationService;
import com.project.mrsisa.service.ShipService;
import com.project.mrsisa.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservation/report", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationReportController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationReportService reservationReportService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private CottageService cottageService; 
    @Autowired
    private UserService userService;
    

    @PostMapping(value="/save", consumes = "application/json")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER') or hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<ReservationReportDTO> saveReservationReport(@RequestBody ReservationReportDTO reportDTO){
        ReservationReport report = new ReservationReport();
        report.setReportText(reportDTO.getReport());
        report.setReservationId(reportDTO.getReservationId());
        report.setClientId(reportDTO.getClientId());
        report.setUnarrivedClient(reportDTO.isUnarrivedClient());
        System.out.println(reportDTO.isSuggestedPenallty());
        report.setPenallty(reportDTO.isSuggestedPenallty());
        report.setStatus(ProcessingStatus.UNPROCESSED);
        if(report.isUnarrivedClient()){
            Client client = clientService.findOne(report.getClientId());
            int penallty = client.getPenaltyNumber();
            client.setPenaltyNumber(penallty+1);
            report.setStatus(ProcessingStatus.APPROVED);
            clientService.save(client);
        }
        report = reservationReportService.save(report);
        return new ResponseEntity<>(new ReservationReportDTO(report), HttpStatus.CREATED);
    }


    @GetMapping(value="/check/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER') or hasRole('SHIP_OWNER') or hasRole('FISHINSTRUCTOR')")
    public ResponseEntity<Boolean> haveReservationReport(@PathVariable Long id){
        ReservationReport rp = reservationReportService.haveReservationReport(id);

        if(rp == null){

            return new ResponseEntity<>(false,HttpStatus.OK);
        }
		System.out.println("reportttt" + rp.getReportText());
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    
    
    @GetMapping(value="/unprocessed")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationReportAdminDTO>> getUnprocessedReport(){
    	List<ReservationReport> reports = reservationReportService.findUnprocessedReport();
    	List <ReservationReportAdminDTO> reportsDTO = new ArrayList<ReservationReportAdminDTO>();
    	
    	Client client;
    	User owner=null;
    	Reservation reservation;
    	Adventure adventure;
    	Ship ship;
    	Cottage cottage;
    	String offerName = "";
    	for(ReservationReport r : reports) {
    		client = clientService.findOne(r.getClientId());
    		reservation = reservationService.getReservationById(r.getReservationId());
    		if (reservation.getOfferType() == OfferType.ADVENTURE) {
    			adventure = adventureService.findOneById(reservation.getOffer().getId());
    			owner = userService.findById(adventure.getOwner().getId());
    			offerName= OfferType.ADVENTURE.toString();
    		}else if(reservation.getOfferType() == OfferType.SHIP) {
    			ship = shipService.findOne(reservation.getOffer().getId());
    			owner = userService.findById(ship.getOwner().getId());
    			offerName=OfferType.SHIP.toString();
    		}else if (reservation.getOfferType() == OfferType.COTTAGE){
    			cottage = cottageService.findOne(reservation.getOffer().getId());
    			owner = userService.findById(cottage.getOwner().getId());
    			offerName=OfferType.COTTAGE.toString();
    		}
    		
    		reportsDTO.add(new ReservationReportAdminDTO(r.getId(), client.getName(), client.getSurname(), client.getEmail(),
			r.isPenallty(), r.getReportText(), owner.getName(), owner.getSurname(), owner.getEmail(),
			reservation.getStartDate(), reservation.getEndDate(), offerName));
    		
    	}
    	return new ResponseEntity<List<ReservationReportAdminDTO>>(reportsDTO, HttpStatus.OK);
    }
    
    @PostMapping(value="/approve", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TextDTO> approveReservationReport(@RequestBody ReservationReportAdminDTO dto){
    	System.out.println();
    	ReservationReport reservationReport = reservationReportService.findOneById(dto.getId());
		if (reservationReport == null) {
			TextDTO  t = new TextDTO("Ne postojei izveštaj o rezervaciji.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.BAD_REQUEST);
		}
    	Client client = clientService.findClientByEmail(dto.getClientEmail());
    	if(dto.getPenalty()) {
    		addPenalty(client);	
    	}
    	reservationReport.setStatus(ProcessingStatus.APPROVED);
    	try {
        	reservationReportService.saveAccept(reservationReport);
        	reservationReportService.sendAcceptMails(dto);
		} catch (ObjectOptimisticLockingFailureException e) {
			TextDTO  t = new TextDTO("Drugi administrator je već odgovorio na ovaj izveštaj o rezervaciji.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.OK);
		}
   
    	TextDTO  t = new TextDTO("Odobrili ste izveštaj o rezervaciji.");
		t.setSuccessfull(true);
    return new ResponseEntity<>(t, HttpStatus.OK);
    }
    
    private void addPenalty(Client client) {
    	int penallty = client.getPenaltyNumber();
    	client.setPenaltyNumber(penallty+1);
        clientService.save(client);
    }
    
    
    @PostMapping(value="/reject", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TextDTO> rejectReservationReport(@RequestBody ReservationReportAdminDTO dto){
    	ReservationReport reservationReport = reservationReportService.findOneById(dto.getId());
		if (reservationReport == null) {
			TextDTO  t = new TextDTO("Ne postojei izveštaj o rezervaciji.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.BAD_REQUEST);
		}

    	reservationReport.setStatus(ProcessingStatus.REJECTED);
    	try {
        	reservationReportService.saveReject(reservationReport);
        	reservationReportService.sendRejectmail(dto);
		} catch (ObjectOptimisticLockingFailureException e) {    
			TextDTO  t = new TextDTO("Drugi administrator je već odgovorio na ovaj izveštaj o rezervaciji.");
			t.setSuccessfull(false);
			return new ResponseEntity<>(t, HttpStatus.OK);
		} 
    	TextDTO  t = new TextDTO("Odbili ste izveštaj o rezervaciji.");
		t.setSuccessfull(true);
    return new ResponseEntity<>(t, HttpStatus.OK);
    }
    

}
