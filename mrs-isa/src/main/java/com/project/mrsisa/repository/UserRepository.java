package com.project.mrsisa.repository;

import com.project.mrsisa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailEquals(String email);

    Optional<User> findById(Long id);

    public Page<User> findAll(Pageable pageable);

	
	@Transactional
    @Query(value = "SELECT * FROM users c WHERE c.deleted is false", nativeQuery = true)
	public List<User> findAllActive();

	@Transactional
    @Query(value = "SELECT * FROM users c WHERE c.deleted is true", nativeQuery = true)
	public List<User> findAllDeleted();
}
