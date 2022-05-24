package com.project.mrsisa.service;

import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.domain.PeriodUnavailability;
import com.project.mrsisa.domain.Reservation;
import com.project.mrsisa.dto.StartEndDateDTO;
import com.project.mrsisa.dto.StartEndDateTimeDefineDTO;
import com.project.mrsisa.repository.PeriodAvailabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodAvailabilitySerivce {

    @Autowired
    private PeriodAvailabilityRepository periodAvailabilityRepository;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    public List<PeriodAvailability> getListOfAvailability(long offerId, LocalDate fromDate, LocalDate untilDate){
        return periodAvailabilityRepository.findCurrentPeriodsOfAvailability(offerId, fromDate, untilDate);
    }

    public List<PeriodAvailability> getListOfUnavailability(long offerId, LocalDate fromDate, LocalDate untilDate){
        return periodAvailabilityRepository.findCurrentPeriodsOfAvailability(offerId, fromDate, untilDate);
    }

	public PeriodAvailability save(PeriodAvailability periodAvailability) {
		return periodAvailabilityRepository.save(periodAvailability);
	}

	public List<PeriodAvailability> getListOfAvailbilityForOffer(Long offerId){	
		return periodAvailabilityRepository.getListOfAvailbilityForOffer(offerId);
		
	}

	
	public List<StartEndDateDTO> intersectionPeriodsForAvailability(List<PeriodAvailability> availability, List<PeriodUnavailability> unavailability, List<Reservation> reservations){
		
		List<StartEndDateTimeDefineDTO> availabilityIntersectionUnavailability = new ArrayList<StartEndDateTimeDefineDTO>();				
		LocalDateTime current = null;
		
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
			for(Reservation r: reservations) {
				if(current.isBefore(r.getStartDateTime()) && period.getEnd().isAfter(r.getEndDate().atStartOfDay())) {
					System.out.println(current);
					System.out.println("Sssssss " + current.format(formatter) );
					System.out.println(r.getStartDate());
					System.out.println("sSSSSssssssss " + r.getStartDateTime().format(formatter));
					
					intersectionAll.add(new StartEndDateDTO(current.format(formatter), r.getStartDateTime().format(formatter), "  " ));
					current = r.getEndDate().atStartOfDay();
				}
			}
			intersectionAll.add(new StartEndDateDTO(current.format(formatter), period.getEnd().format(formatter), " " ));
		}	
		
	return 	intersectionAll;
	}
	
}
