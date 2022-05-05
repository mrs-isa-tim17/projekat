package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.repository.ExperienceReviewRepository;

@Service
public class ExperienceReviewService {
	
	@Autowired
	private ExperienceReviewRepository experienceReviewRepository;

	public double getReatingByOfferId(long id){
		return experienceReviewRepository.findOffersCurrentPriceById(id).orElse(0.0);
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
	
	public List<ExperienceReview> findAllByOfferId(Long id){
		return experienceReviewRepository.findAllByOfferId(id);
	}

}
