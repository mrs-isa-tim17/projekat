package com.project.mrsisa.service;

import java.util.List;

import com.project.mrsisa.domain.OfferType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.repository.ExperienceReviewRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExperienceReviewService {
	
	@Autowired
	private ExperienceReviewRepository experienceReviewRepository;
	@Autowired
	private ClientService clientService;

	public double getReatingByOfferId(long id, OfferType offerType){
		return experienceReviewRepository.findOffersCurrentPriceById(id, offerType.getValue()).orElse(0.0);
	}
	
	public ExperienceReview save(ExperienceReview experienceReview) {
		return experienceReviewRepository.save(experienceReview);
	}
	
	public List<ExperienceReview> findAll(){
		return experienceReviewRepository.findAll();
	}
	
	
	public ExperienceReview findOneById(Long id) {
		return experienceReviewRepository.findOneById(id);
	}

	@Transactional
	public List<ExperienceReview> findAllByOfferId(Long id){
		List<ExperienceReview> exRevs = experienceReviewRepository.findAllByOfferId(id);
		for (ExperienceReview e: exRevs) {
			e.setClient(clientService.findOne(e.getClient().getId()));
		}
		return exRevs;

	}

}
