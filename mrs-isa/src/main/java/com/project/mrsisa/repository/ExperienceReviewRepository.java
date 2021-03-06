package com.project.mrsisa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.ExperienceReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExperienceReviewRepository extends JpaRepository<ExperienceReview, Long> {
	
	public Page<ExperienceReview> findAll(Pageable pageable);

	public ExperienceReview findOneById(Long id);

    @Query(value="SELECT * FROM experience_review r WHERE r.offer_id=?1 and r.status = 1", nativeQuery=true)
    public List<ExperienceReview> findAllByOfferId(Long id);

    //@Transactional//status reviewa
    @Query(value = "SELECT AVG(rate) FROM experience_review p WHERE p.offer_id = ?1 and p.rate <> -1 and p.offer_type = ?2 and p.status = 1",
            nativeQuery = true)
    Optional<Double> findOffersCurrentRatingById(Long offerId, int offerType);

    @Query(value="SELECT * FROM experience_review r WHERE r.status=0", nativeQuery=true)
	public List<ExperienceReview> findAllUnprocessedExperienceReview();

}