package com.example.projectmanagement.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentDTO {
    private Long id;
    private String content;
    private Long taskId;
    private Long userId;
    private Timestamp createdAt;
}
