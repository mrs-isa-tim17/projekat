package com.project.mrsisa.repository;

import com.project.mrsisa.domain.ExperienceReview;
import com.project.mrsisa.domain.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ExperienceReviewRepository extends JpaRepository<ExperienceReview, Long> {

    @Transactional//status reviewa
    @Query(value = "SELECT AVG(rate) FROM experience_review p WHERE p.offer_id = ?1",
            nativeQuery = true)
    Optional<Double> findOffersCurrentPriceById(Long offerId);
}
