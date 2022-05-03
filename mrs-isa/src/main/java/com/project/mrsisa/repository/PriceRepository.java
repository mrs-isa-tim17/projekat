package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PriceRepository extends JpaRepository<Pricelist, Long> {

    @Transactional
    @Query(value = "SELECT * FROM pricelist p WHERE p.offer_id = ?1 and p.end_date < CURRENT_DATE",
            nativeQuery = true)
    Pricelist findOffersCurrentPriceById(Long offerId);
}
