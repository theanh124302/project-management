package com.example.projectmanagement.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate<T> {
    private HttpStatus status;
    private String message;
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private T data;
}
