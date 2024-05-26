package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.UserProject;
import com.example.projectmanagement.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask,Long> {
    Optional<UserTask> findByUserIdAndTaskId(Long userId, Long taskId);
    List<UserTask> findByUserId(Long userId);
    List<UserTask> findByTaskId(Long taskId);
    @Modifying
    @Query("DELETE FROM UserTask u WHERE u.id = ?1")
    void deleteById(Long id);
}