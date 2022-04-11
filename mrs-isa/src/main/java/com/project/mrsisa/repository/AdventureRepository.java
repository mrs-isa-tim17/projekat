package com.project.mrsisa.repository;
import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Adventure;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
	public Page<Adventure> findAll(Pageable pageable);
}
