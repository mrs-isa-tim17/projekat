package com.project.mrsisa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{

	public Page<Admin> findAll(Pageable pageable);
	
	public Admin findOneById(Long id);

}
