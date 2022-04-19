package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Place;
import com.project.mrsisa.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;
	
	
	public Place save(Place place) {
		return placeRepository.save(place);
	}
	
	public List<Place> findAll(){
		return placeRepository.findAll();
	}
	
	public Place findOneByPlaceNameAndCountry(String placeName, String country) {
		List<Place> places = placeRepository.findByPlaceNameIsIgnoreCaseAndCountryIsIgnoreCase(placeName, country);
		if (places.size() == 0)
			return null;
		return places.get(0);		
	}
}
