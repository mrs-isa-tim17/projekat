package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.ExperienceReview;

public interface ExperienceReviewRepository extends JpaRepository<ExperienceReview, Long> {
	
	public Page<ExperienceReview> findAll(Pageable pageable);

	public ExperienceReview findOneById(Long id);
	
	public List<ExperienceReview> findAllByOfferId(Long id);

}