package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.LoyaltyPoints;
import com.project.mrsisa.domain.LoyaltyScale;
import com.project.mrsisa.domain.UserTypeLoyaltyPoints;
import com.project.mrsisa.repository.LoyaltyPointsRepository;

@Service
public class LoyaltyPointsService {
	@Autowired
	private LoyaltyPointsRepository loyaltyPointsRepository;
	
	public List<LoyaltyPoints> findPointsByUserTypePoints(UserTypeLoyaltyPoints type){
		return loyaltyPointsRepository.findPointsByUserTypePoints(type.getValue());
	}
	
	public LoyaltyPoints findOneById(long id) {
		return loyaltyPointsRepository.findOneById(id);
	}

	public void save(LoyaltyPoints loyaltyPoints) {
		loyaltyPointsRepository.save(loyaltyPoints);
	}

	public LoyaltyPoints findActivePointsByType(UserTypeLoyaltyPoints user) {
		return loyaltyPointsRepository.findActivePointsByType(user.getValue());
	}

	public LoyaltyPoints findCurrentLoyaltyPointForUser(UserTypeLoyaltyPoints type){
		List<LoyaltyPoints> res = loyaltyPointsRepository.findCurrentActivePointsByType(type.getValue());
		if (res.size() > 0)
			return res.get(0);
		return null;
	}

}
