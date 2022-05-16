package com.project.mrsisa.service;

import com.project.mrsisa.domain.PeriodUnavailability;
import com.project.mrsisa.repository.PeriodUnavailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeriodUnavailabilityService {

    @Autowired
    private PeriodUnavailabilityRepository periodUnavailabilityRepository;

    public List<PeriodUnavailability> getListOfUnavailability(long offerId, LocalDate fromDate, LocalDate untilDate){
        return periodUnavailabilityRepository.findCurrentPeriodsOfUnavailability(offerId, fromDate, untilDate);
    }
    
	public PeriodUnavailability save(PeriodUnavailability periodUnavailability) {
		return periodUnavailabilityRepository.save(periodUnavailability);
	}
	
	public List<PeriodUnavailability>  getListOfUnavailbilityForOffer(Long offerId){
		return periodUnavailabilityRepository.getListOfUnavailbilityForOffer(offerId);
	}
}
