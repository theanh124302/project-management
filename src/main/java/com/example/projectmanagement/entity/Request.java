package com.example.projectmanagement.entity;

import com.example.projectmanagement.enums.Method;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="request")
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bodyJson;
    private String url;
    private String token;
    private Method method;
}
