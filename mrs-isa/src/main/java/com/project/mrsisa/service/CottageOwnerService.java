package com.project.mrsisa.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.CottageOwner;
import com.project.mrsisa.repository.CottageOwnerRepository;

@Service
public class CottageOwnerService {
	@Autowired
	private CottageOwnerRepository coRepository;

	public CottageOwner save(CottageOwner co) {
		return coRepository.save(co);
	}
	
	public List<CottageOwner> findAll(){
		return coRepository.findAll();
	}
	
	public CottageOwner findOne(Long id) {
		return coRepository.findById(id).orElseGet(null);
	}

    public void setCottageOwnerRepository(CottageOwnerRepository cottageOwnerRepository) {
		this.coRepository = cottageOwnerRepository;
    }
}
