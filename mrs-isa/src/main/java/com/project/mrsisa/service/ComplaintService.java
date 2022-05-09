package com.project.mrsisa.service;

import com.project.mrsisa.domain.Complaint;
import com.project.mrsisa.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint save(Complaint complaint){
        return complaintRepository.save(complaint);
    }
}
