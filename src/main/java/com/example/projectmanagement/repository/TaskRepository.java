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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE lower(t.name) LIKE lower(concat('%', :name, '%'))")
    Page<Task> findByName(String name, Pageable pageable);
    @Query("SELECT t FROM Task t WHERE lower(t.name) LIKE lower(concat('%', :name, '%')) AND t.projectId = :projectId")
    Page<Task> findByProjectIdAndName(Long projectId, String name, Pageable pageable);
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    Page<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status, Pageable pageable);
    List<Task> findByApiIdAndLifeCycle(Long apiId, LifeCycle lifeCycle);
    Long countByProjectIdAndStatus(Long projectId, TaskStatus status);
    Long countByProjectId(Long projectId);

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE project_id = :projectId AND DAY(due_date) = :day AND YEAR(due_date) = YEAR(CURDATE()) AND MONTH(due_date) = MONTH(CURDATE())", nativeQuery = true)
    Long countByProjectIdAndDueDate(@Param("projectId") Long projectId, @Param("day") int day);

    @Query(value = "SELECT COUNT(*) FROM tasks t " +
            "JOIN user_task ut ON t.id = ut.task_id " +
            "WHERE ut.user_id = :userId " +
            "AND DAY(t.due_date) = :day " +
            "AND YEAR(t.due_date) = YEAR(CURDATE()) " +
            "AND MONTH(t.due_date) = MONTH(CURDATE())",
            nativeQuery = true)
    Long countByUserIdAndDueDate(@Param("userId") Long userId, @Param("day") int day);

    @Query(value = "SELECT * FROM tasks t " +
            "JOIN user_task ut ON t.id = ut.task_id " +
            "WHERE ut.user_id = :userId " +
            "AND t.project_id = :projectId ",
            nativeQuery = true)
    Page<Task> findByUserIdAndProjectId(Long userId, Long projectId, Pageable pageable);

    @Query(value = "SELECT * FROM tasks t " +
            "JOIN user_task ut ON t.id = ut.task_id " +
            "WHERE ut.user_id = :userId " +
            "AND t.project_id = :projectId " +
            "AND lower(t.name) LIKE lower(concat('%', :name, '%'))",
            nativeQuery = true)
    Page<Task> findByUserIdAndProjectIdAndName(Long userId, Long projectId, String name, Pageable pageable);
    @Query(value = "SELECT * FROM tasks t " +
            "JOIN user_task ut ON t.id = ut.task_id " +
            "WHERE ut.user_id = :userId " +
            "AND t.project_id = :projectId " +
            "AND lower(t.name) LIKE lower(concat('%', :name, '%')) " +
            "AND t.status = :status",
            nativeQuery = true)
    Page<Task> findByUserIdAndProjectIdAndNameAndStatus(Long userId, Long projectId, String name, TaskStatus status, Pageable pageable);

    @Query(value = "SELECT * FROM tasks t " +
            "WHERE t.project_id = :projectId " +
            "AND lower(t.name) LIKE lower(concat('%', :name, '%')) " +
            "AND t.status = :status",
            nativeQuery = true)
    Page<Task> findByProjectIdAndNameAndStatus(Long projectId, String name, TaskStatus status, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE project_id = :projectId AND MONTH(due_date) = :month AND YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    Long countByProjectIdAndDueMonth(@Param("projectId") Long projectId, @Param("month") int month);
}
