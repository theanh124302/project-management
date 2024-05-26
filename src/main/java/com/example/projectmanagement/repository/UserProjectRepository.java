package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    Optional<UserProject> findByUserIdAndProjectId(Long userId, Long projectId);
    List<UserProject> findByUserId(Long userId);
    List<UserProject> findByProjectId(Long projectId);
    List<UserProject> findByProjectIdAndUserId(Long projectId, Long userId);
    @Modifying
    @Query("DELETE FROM UserProject u WHERE u.id = ?1")
    void deleteById(Long id);
}
