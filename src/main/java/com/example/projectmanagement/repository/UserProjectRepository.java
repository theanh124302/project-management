package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Long> {
    UserProject findByUserIdAndProjectId(Long userId, Long projectId);
    List<UserProject> findByUserId(Long userId);
    List<UserProject> findByProjectId(Long projectId);
    void deleteByUserIdAndProjectId(Long userId, Long projectId);
}
