package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE lower(t.name) LIKE lower(concat('%', :name, '%'))")
    Page<Task> findByName(String name, Pageable pageable);
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    Page<Task> findByExecutorId(Long executorId, Pageable pageable);
    Page<Task> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable);
}
