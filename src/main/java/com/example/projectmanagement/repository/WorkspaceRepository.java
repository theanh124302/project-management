package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Long>{
    @Query("SELECT w FROM Workspace w WHERE lower(w.name) LIKE lower(concat('%', :name, '%'))")
    Page<Workspace> findByName(String name, Pageable pageable);
    Page<Workspace> findByCreatorId(Long creatorId, Pageable pageable);
}
