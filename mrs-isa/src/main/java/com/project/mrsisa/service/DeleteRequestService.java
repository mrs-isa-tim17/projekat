package com.project.mrsisa.service;

import com.project.mrsisa.domain.DeleteRequest;
import com.project.mrsisa.repository.DeleteRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteRequestService {

    @Autowired
    private DeleteRequestRepository deleteRequestRepository;

    public DeleteRequest save(DeleteRequest deleteRequest){
        return deleteRequestRepository.save(deleteRequest);
    }

}
