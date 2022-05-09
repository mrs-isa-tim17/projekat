package com.project.mrsisa.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.mrsisa.domain.RegistrationRequest;

@Repository
public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {
	
	public Page<RegistrationRequest> findAll(Pageable pageable);

	public RegistrationRequest findOneById(Long id);
	
	public List<RegistrationRequest> findByStatus(int status);
	
	
	@Transactional
    @Query(value = "SELECT * FROM registration_request r WHERE r.status=0", nativeQuery = true)
    public List<RegistrationRequest> findUnprocessedRegistrationRequest();

	

}