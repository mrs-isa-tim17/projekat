package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Complaint;
<<<<<<< HEAD
import com.project.mrsisa.domain.Image;
=======
import com.project.mrsisa.domain.RegistrationRequest;

import java.util.List;

>>>>>>> 7e610c1041778d3d68cb71499e24265595574d99
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

<<<<<<< HEAD
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    public List<Complaint> findAllByOfferId(Long id);
=======
	
	@Transactional
    @Query(value = "SELECT * FROM complaint c WHERE c.status=0", nativeQuery = true)
    public List<Complaint> findUnprocessedComplaint();

	public Complaint findOneById(Long id);
	
>>>>>>> 7e610c1041778d3d68cb71499e24265595574d99
}
