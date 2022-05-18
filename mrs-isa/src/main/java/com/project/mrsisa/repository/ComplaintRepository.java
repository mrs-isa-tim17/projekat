package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Complaint;
import com.project.mrsisa.domain.RegistrationRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	
	@Transactional
    @Query(value = "SELECT * FROM complaint c WHERE c.status=0", nativeQuery = true)
    public List<Complaint> findUnprocessedComplaint();

	public Complaint findOneById(Long id);
	
}
