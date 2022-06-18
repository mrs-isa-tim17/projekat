package com.project.mrsisa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mrsisa.domain.Admin;
import com.project.mrsisa.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public List<Admin> findAll(){
		return adminRepository.findAll();
	}
	
	public Admin findOne(Long id) {
		return adminRepository.findOneById(id);
	}
}
