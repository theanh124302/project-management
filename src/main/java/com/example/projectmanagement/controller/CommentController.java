package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.CommentDTO;
import com.example.projectmanagement.service.CommentService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<CommentDTO>> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO createdComment = commentService.create(commentDTO);
        if (createdComment != null) {
            return ResponseEntity.ok(ResponseTemplate.<CommentDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Comment created successfully")
                    .data(createdComment)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<CommentDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create comment")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<CommentDTO>> updateComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.update(commentDTO);
        if (updatedComment != null) {
            return ResponseEntity.ok(ResponseTemplate.<CommentDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Comment updated successfully")
                    .data(updatedComment)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<CommentDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Comment not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<CommentDTO>> deleteComment(@RequestParam Long id) {
        CommentDTO deletedComment = commentService.delete(id);
        if (deletedComment != null) {
            return ResponseEntity.ok(ResponseTemplate.<CommentDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Comment deleted successfully")
                    .data(deletedComment)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<CommentDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Comment not found")
                    .build());
        }
    }

    @GetMapping("/findByTaskId")
    public ResponseEntity<ResponseTemplate<List<CommentDTO>>> findCommentsByApiId(@RequestParam Long taskId,
                                                                                  @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CommentDTO> comments = commentService.findByTaskId(taskId, pageable);
        long totalItems = commentService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<CommentDTO>>builder()
                .status(HttpStatus.OK)
                .message("Comments found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(comments)
                .build());
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<ResponseTemplate<List<CommentDTO>>> findCommentsByUserId(@RequestParam Long userId,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CommentDTO> comments = commentService.findByUserId(userId, pageable);
        long totalItems = commentService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<CommentDTO>>builder()
                .status(HttpStatus.OK)
                .message("Comments found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(comments)
                .build());
    }
}
