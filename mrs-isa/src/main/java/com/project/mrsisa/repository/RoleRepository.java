package com.project.mrsisa.repository;

import com.project.mrsisa.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
    
    public Role findOneById(Long id);
}
