package com.project.mrsisa.service;

import com.project.mrsisa.repository.ExperienceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceReviewService {

    @Autowired
    private ExperienceReviewRepository experienceReviewRepository;

    public double getReatingByOfferId(long id){
        return experienceReviewRepository.findOffersCurrentPriceById(id).orElse(0.0);
    }
}
