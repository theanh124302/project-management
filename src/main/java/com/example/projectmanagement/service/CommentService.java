package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.CommentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    CommentDTO create(CommentDTO commentDTO);
    CommentDTO update(CommentDTO commentDTO);
    CommentDTO delete(Long id);
    CommentDTO findById(Long id);
    List<CommentDTO> findByTaskId(Long taskId, Pageable pageable);
    List<CommentDTO> findByUserId(Long userId, Pageable pageable);
    Long count();
}
