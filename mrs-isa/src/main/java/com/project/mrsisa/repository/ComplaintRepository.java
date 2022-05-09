package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
