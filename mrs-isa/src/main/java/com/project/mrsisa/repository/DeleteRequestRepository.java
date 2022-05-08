package com.project.mrsisa.repository;

import com.project.mrsisa.domain.DeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DeleteRequestRepository extends JpaRepository<DeleteRequest, Long> {

    @Transactional
    @Query(value = "SELECT * FROM delete_request p WHERE p.user_id = ?1",
            nativeQuery = true)
    DeleteRequest findByUserRef(Long id);

}
