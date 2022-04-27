package com.project.mrsisa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.mrsisa.domain.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public Page<Client> findAll(Pageable pageable);
	
	//@Modifying
	//@Query("update User u set u.name = ?1, u.surname = ?2, u.password = ?3, phonenumber=?4 where u.id = ?5")
	//void setUserInfoById(String name, String surname, String password, String phoneNumber, Integer id);
}
