package com.project.mrsisa.service;

import com.project.mrsisa.domain.PeriodAvailability;
import com.project.mrsisa.repository.PeriodAvailabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class PeriodAvailabilitySerivce {

    @Autowired
    private PeriodAvailabilityRepository periodAvailabilityRepository;


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

}
