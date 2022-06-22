package com.project.mrsisa.service;

import java.util.List;

import com.project.mrsisa.domain.LoyaltyPoints;
import com.project.mrsisa.domain.UserType;
import com.project.mrsisa.domain.UserTypeLoyaltyPoints;
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

    public List<LoyaltyScale> findCurrentLoyaltyScaleForUser(Long roleId) {
		List<LoyaltyScale> res = loyaltyScaleRepository.findCurrentActiveScalesByType(roleId);
		if (res.size() >= 3){
			return res.subList(0, 3);
		}
		return null;
    }

	public LoyaltyScale findCurrentLoyaltyScaleByUserType(long roleId, UserType userType) {
		List<LoyaltyScale> res = loyaltyScaleRepository.findCurrentActivePointsByUserType(roleId, userType.getValue());
		if (res.size() > 0)
			return res.get(0);
		return null;
	}
}
