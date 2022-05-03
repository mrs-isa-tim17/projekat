package com.project.mrsisa.service;

import com.project.mrsisa.domain.Pricelist;
import com.project.mrsisa.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public double getCurrentPriceOfOffer(long offerID){
        Pricelist p =  priceRepository.findOffersCurrentPriceById(offerID);
        if (p == null)
            return 0;
        return p.getPrice();
    }
}
