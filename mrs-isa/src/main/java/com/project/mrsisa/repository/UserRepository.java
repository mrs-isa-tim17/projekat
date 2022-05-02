package com.project.mrsisa.repository;

import com.project.mrsisa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailEquals(String email);

    Optional<User> findById(Long id);

    public Page<User> findAll(Pageable pageable);
}
