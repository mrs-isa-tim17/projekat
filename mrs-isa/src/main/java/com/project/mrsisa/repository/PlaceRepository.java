package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Place;
import org.springframework.data.jpa.repository.Query;

public interface PlaceRepository extends JpaRepository<Place, Long>{
	@Query("select p from Place p where upper(p.placeName) = upper(?1) and upper(p.country) = upper(?2)")
	List<Place> findByPlaceNameIsIgnoreCaseAndCountryIsIgnoreCase(String placeName, String country);

	//public List<Place> findAllByPlaceNameAndByCountry(String placeName, String country);
}
