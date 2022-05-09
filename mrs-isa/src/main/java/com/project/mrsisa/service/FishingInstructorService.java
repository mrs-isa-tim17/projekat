package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.FishingInstructor;
import com.project.mrsisa.repository.FishingInstructorRepository;

@Service
public class FishingInstructorService {
	
	@Autowired
	private FishingInstructorRepository fishingInstructorRepository;

	public FishingInstructor save(FishingInstructor fishinginstructor) {
		return fishingInstructorRepository.save(fishinginstructor);
	}
	
	public List<FishingInstructor> findAll(){
		return fishingInstructorRepository.findAll();
	}
	
	public FishingInstructor findOne(Long id) {
		return fishingInstructorRepository.findOneById(id);
	}
}
