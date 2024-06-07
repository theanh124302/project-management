package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE lower(t.name) LIKE lower(concat('%', :name, '%'))")
    Page<Task> findByName(String name, Pageable pageable);
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    Page<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status, Pageable pageable);
    Page<Task> findByApiIdAndLifeCycle(Long apiId, LifeCycle lifeCycle, Pageable pageable);
    Long countByProjectIdAndStatus(Long projectId, TaskStatus status);
    Long countByProjectId(Long projectId);

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE project_id = :projectId AND DAY(due_date) = :day AND YEAR(due_date) = YEAR(CURDATE()) AND MONTH(due_date) = MONTH(CURDATE())", nativeQuery = true)
    Long countByProjectIdAndDueDate(@Param("projectId") Long projectId, @Param("day") int day);

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE project_id = :projectId AND MONTH(due_date) = :month AND YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    Long countByProjectIdAndDueMonth(@Param("projectId") Long projectId, @Param("month") int month);

}
