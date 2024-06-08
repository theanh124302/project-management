package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByProjectId(Long projectId, Pageable pageable);
    Optional<Event> findByTaskId(Long taskId);
    void deleteByTaskId(Long taskId);
}
