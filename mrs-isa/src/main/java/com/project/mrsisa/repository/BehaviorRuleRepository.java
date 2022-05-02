package com.project.mrsisa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mrsisa.domain.BehaviorRule;

public interface BehaviorRuleRepository extends JpaRepository<BehaviorRule, Long> {
	
	public Page<BehaviorRule> findAll(Pageable pageable);

	public BehaviorRule findOneById(Long id);
	
	public List<BehaviorRule> findAllByOfferId(Long id);

}
