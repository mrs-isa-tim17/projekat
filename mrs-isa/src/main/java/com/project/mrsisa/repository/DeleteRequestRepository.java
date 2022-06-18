package com.project.mrsisa.repository;

import com.project.mrsisa.domain.DeleteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DeleteRequestRepository extends JpaRepository<DeleteRequest, Long> {

    @Transactional
    @Query(value = "SELECT * FROM delete_request p WHERE p.user_id = ?1 and p.status=0",
            nativeQuery = true)
    DeleteRequest findByUserRef(Long id);
    
    @Transactional
    @Query(value="SELECT * FROM delete_request p WHERE p.status=0", nativeQuery = true)
    List<DeleteRequest> findUnprocessedDeleteRequest();
    
    public DeleteRequest findOneById(Long id);

}
