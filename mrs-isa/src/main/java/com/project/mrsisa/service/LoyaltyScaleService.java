package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.repository.LoyaltyScaleRepository;

@Service
public class LoyaltyScaleService {
	
	@Autowired
	private LoyaltyScaleRepository loyaltyScaleRepository;

	public LoyaltyScale loyaltyScalesGreaterMinimumTrashhold(int points) {
		List<LoyaltyScale> loyaltyScales = loyaltyScaleRepository.findByTrashholdIsLessThan(points);
		if (loyaltyScales==null || loyaltyScales.size()==0) {
			return new LoyaltyScale();
		}
		LoyaltyScale result = loyaltyScales.get(0);
		for (LoyaltyScale loyaltyScale : loyaltyScales) {
			if (loyaltyScale.getTrashhold() < result.getTrashhold()) {
				result = loyaltyScale;
			}
		}
		return result;
	}
	
	public List<LoyaltyScale> findScaleByRole(Long roleId){
		return loyaltyScaleRepository.findScaleByRole(roleId);
	}
	
	public LoyaltyScale findOneById(long id) {
		return loyaltyScaleRepository.findOneById(id);
	}

	public void save(LoyaltyScale loyaltyScale) {
		loyaltyScaleRepository.save(loyaltyScale);
		
	}
	
}
