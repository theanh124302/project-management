package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.CommentDTO;
import com.example.projectmanagement.entity.Comment;
import com.example.projectmanagement.repository.CommentRepository;
import com.example.projectmanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setApiId(commentDTO.getApiId());
        comment.setUserId(commentDTO.getUserId());
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
    public CommentDTO delete(CommentDTO commentDTO) {
        Optional<Comment> existingCommentOptional = commentRepository.findById(commentDTO.getId());
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
    public List<CommentDTO> findByApiId(Long apiId, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findByApiId(apiId, pageable);
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
        commentDTO.setApiId(comment.getApiId());
        commentDTO.setUserId(comment.getUserId());
        return commentDTO;
    }

//    private Comment convertToEntity(CommentDTO commentDTO) {
//        Comment comment = new Comment();
//        comment.setId(commentDTO.getId());
//        comment.setContent(commentDTO.getContent());
//        comment.setStatus(commentDTO.getStatus());
//        comment.setApiId(commentDTO.getApiId());
//        comment.setUserId(commentDTO.getUserId());
//        comment.setCreatedAt(commentDTO.getCreatedAt());
//        return comment;
//    }
}
