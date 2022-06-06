package com.project.mrsisa.service;

import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.PeriodUnavailability;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.domain.SaleAppointment;
import com.project.mrsisa.dto.HelpObjectDateTime;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.StartEndDateTimeDefineDTO;
import com.project.mrsisa.dto.client.OfferHistoryReservationDTO;
import com.project.mrsisa.repository.PeriodAvailabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PeriodAvailabilitySerivce {

    @Autowired
    private PeriodAvailabilityRepository periodAvailabilityRepository;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    public List<PeriodAvailability> getListOfAvailability(long offerId, LocalDateTime fromDate, LocalDateTime untilDate){
        return periodAvailabilityRepository.findCurrentPeriodsOfAvailability(offerId, fromDate, untilDate);
    }

    public List<PeriodAvailability> getListOfUnavailability(long offerId, LocalDateTime fromDate, LocalDateTime untilDate){
        return periodAvailabilityRepository.findCurrentPeriodsOfAvailability(offerId, fromDate, untilDate);
    }

	public PeriodAvailability save(PeriodAvailability periodAvailability) {
		return periodAvailabilityRepository.save(periodAvailability);
	}

	public List<PeriodAvailability> getListOfAvailbilityForOffer(Long offerId){	
		return periodAvailabilityRepository.getListOfAvailbilityForOffer(offerId);
		
	}

	
	public List<StartEndDateDTO> intersectionPeriodsForAvailability(List<PeriodAvailability> availability, List<PeriodUnavailability> unavailability, List<Reservation> reservations, List<SaleAppointment> actions){
		
		List<StartEndDateTimeDefineDTO> availabilityIntersectionUnavailability = new ArrayList<StartEndDateTimeDefineDTO>();				
		LocalDateTime current = null;

		List<HelpObjectDateTime> actionsReservations = new ArrayList<HelpObjectDateTime>();

		for (Reservation r : reservations){
			HelpObjectDateTime dto = new HelpObjectDateTime();
			dto.setStart(r.getStartDateTime());
			dto.setEnd(r.getEndDateTime());
			actionsReservations.add(dto);
		}
		for (SaleAppointment sa : actions){
			HelpObjectDateTime dto = new HelpObjectDateTime();
			dto.setStart(sa.getStartSaleDate());
			dto.setEnd(sa.getStartSaleDate().plusHours((long) sa.getDuration()));
			actionsReservations.add(dto);
		}


		Collections.sort(actionsReservations, new Comparator<HelpObjectDateTime>() {
			@Override
			public int compare(HelpObjectDateTime c1, HelpObjectDateTime c2) {
				int dateCompare = c1.getStart().compareTo(
						c2.getStart());
				return dateCompare;
			}
		});

		for(PeriodAvailability avail : availability) {
			current = avail.getStartDate();
			for(PeriodUnavailability unavail : unavailability) {
				System.out.println("avail: " + "start:  " + avail.getStartDate() +"end:  " + avail.getEndDate());
				System.out.println("unavail : " + "start:  " + unavail.getStartDate()+"    end: " + unavail.getEndDate());
				if((current.isBefore(unavail.getStartDate())) && (avail.getEndDate().isAfter(unavail.getEndDate()))) {
					availabilityIntersectionUnavailability.add(new StartEndDateTimeDefineDTO(current, unavail.getStartDate()));
					current = unavail.getEndDate();
					System.out.println("DDDDD");
					
				}
			}
			availabilityIntersectionUnavailability.add(new StartEndDateTimeDefineDTO(current, avail.getEndDate()));
		}
		
		System.out.println("MILICAA: " + availabilityIntersectionUnavailability.size());
		
		List<StartEndDateDTO> intersectionAll = new ArrayList<StartEndDateDTO>();
		for(StartEndDateTimeDefineDTO period : availabilityIntersectionUnavailability) {
			current =  period.getStart();
			for(HelpObjectDateTime r: actionsReservations) {
				if(current.isBefore(r.getStart()) && period.getEnd().isAfter(r.getEnd())) {
					System.out.println(current);
					System.out.println("Sssssss " + current.format(formatter) );
					System.out.println(r.getStart());
					System.out.println("sSSSSssssssss " + r.getStart().format(formatter));
					
					intersectionAll.add(new StartEndDateDTO(current.format(formatter), r.getStart().format(formatter), "  " ));
					current = r.getEnd();
				}
			}
			intersectionAll.add(new StartEndDateDTO(current.format(formatter), period.getEnd().format(formatter), " " ));
		}	
		
	return 	intersectionAll;
	}
	
}
