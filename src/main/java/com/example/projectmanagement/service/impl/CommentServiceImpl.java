package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.CommentDTO;
import com.example.projectmanagement.entity.Comment;
import com.example.projectmanagement.repository.CommentRepository;
import com.example.projectmanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        comment.setCreatedAt(Timestamp.from(Instant.now()));
        return convertToDTO(commentRepository.save(comment));
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        Optional<Comment> existingCommentOptional = commentRepository.findById(commentDTO.getId());
        if (existingCommentOptional.isPresent()) {
            Comment existingComment = existingCommentOptional.get();
            existingComment.setContent(commentDTO.getContent());
            return convertToDTO(commentRepository.save(existingComment));
        } else {
            return null; // Handle the case where the comment doesn't exist
        }
    }

    @Override
    public CommentDTO delete(Long id) {
        Optional<Comment> existingCommentOptional = commentRepository.findById(id);
        if (existingCommentOptional.isPresent()) {
            Comment existingComment = existingCommentOptional.get();
            commentRepository.delete(existingComment);
            return convertToDTO(existingComment);
        } else {
            return null; // Handle the case where the comment doesn't exist
        }
    }

    @Override
    public CommentDTO findById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        return commentOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<CommentDTO> findByTaskId(Long taskId, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findByTaskId(taskId, pageable);
        return commentPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<CommentDTO> findByUserId(Long userId, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findByUserId(userId, pageable);
        return commentPage.getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return commentRepository.count();
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setTaskId(comment.getTaskId());
        commentDTO.setUserId(comment.getUserId());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setTaskId(commentDTO.getTaskId());
        comment.setUserId(commentDTO.getUserId());
        comment.setCreatedAt(commentDTO.getCreatedAt());
        return comment;
    }
}
