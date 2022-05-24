package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Client;
import com.project.mrsisa.domain.Complaint;
import com.project.mrsisa.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    public List<Complaint> findAllByOfferId(Long id);
}
