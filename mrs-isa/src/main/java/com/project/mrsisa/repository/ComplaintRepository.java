package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Complaint;


import com.project.mrsisa.domain.Image;

import com.project.mrsisa.domain.RegistrationRequest;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    public List<Complaint> findAllByOfferId(Long id);

	
	@Transactional
    @Query(value = "SELECT * FROM complaint c WHERE c.status=0", nativeQuery = true)
    public List<Complaint> findUnprocessedComplaint();

	public Complaint findOneById(Long id);
	

}
