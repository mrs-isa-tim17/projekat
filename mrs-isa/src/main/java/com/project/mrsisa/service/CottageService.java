package com.project.mrsisa.service;

import java.util.List;
;
import com.project.mrsisa.domain.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Cottage;
import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.repository.CottageRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CottageService {
	private final Logger LOG = LoggerFactory.getLogger(CottageService.class);


	@Autowired
	private CottageRepository cottageRepository;

	@Autowired
	private ReservationService reservationService;
	
	public Cottage save(Cottage cottage) {
		return cottageRepository.save(cottage);
	}
	
	public List<Cottage> findAll() {
		return cottageRepository.findAll();
	}
	
	
	public List<Cottage> getCottagesByOwner(CottageOwner owner){
		return cottageRepository.findByOwner(owner);
	}

	@Cacheable("cottage")
	public Cottage findOne(Long id) {
		LOG.info("Cottage with id: " + id + " successfully cached!");
		return cottageRepository.findById(id).orElseGet(null);
	}
	
	public void remove(Long id) {
		cottageRepository.deleteById(id);
	}

	public List<Offer> findAllByClientId(Long id) {
		return cottageRepository.findAllByClientId(id);
	}
	
	public List<Cottage> findActiveCottages() {
		return cottageRepository.findActiveCottages();
	}
	
	public List<Cottage> findDeletedCottages(){
		return cottageRepository.findDeletedCottages();
	}

    public Cottage findOneTryOccupation(Long id) {
		return cottageRepository.findOneTryOccupation(id);
    }

	public CottageRepository getCottageRepository() {
		return cottageRepository;
	}

	public void setCottageRepository(CottageRepository cottageRepository) {
		this.cottageRepository = cottageRepository;
	}

	@CacheEvict(cacheNames = {"cottage"}, allEntries = true)
	void removeFromCache(){
		LOG.info("Products removed from cache!");
	}
	@CacheEvict(cacheNames = {"cottage"},key = "#id")
	public void removeOneFromCacheById(@Param("id") long id){
		LOG.info("Cottage: " + id + " removed from cache!");
	}

	@Transactional
	public boolean deleteCottage(long id) {
		Cottage cottage = cottageRepository.findOneTryOccupation(id);
		if (cottage != null && !(reservationService.haveFutureReservations(id))) {
			cottage.setDeleted(true);
			save(cottage);				// logičko brisanje
			removeOneFromCacheById(cottage.getId());
			return true;
		}
		return false;
	}
}
