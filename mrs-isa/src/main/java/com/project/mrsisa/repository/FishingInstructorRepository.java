package com.project.mrsisa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.FishingInstructor;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor,Long>{

	public Page<FishingInstructor> findAll(Pageable pageable);
	
	public FishingInstructor findOneById(Long id);


}
