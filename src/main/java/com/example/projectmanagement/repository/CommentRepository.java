package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByApiId(Long apiId, org.springframework.data.domain.Pageable pageable);
    Page<Comment> findByUserId(Long userId, org.springframework.data.domain.Pageable pageable);
}
