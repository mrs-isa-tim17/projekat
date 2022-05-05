package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	public Page<Image> findAll(Pageable pageable);

	public Image findOneById(Long id);
	
	public List<Image> findAllByOfferId(Long id);

}
