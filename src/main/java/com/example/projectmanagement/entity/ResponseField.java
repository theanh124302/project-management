package com.example.projectmanagement.entity;

import jakarta.persistence.*;
        import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="response_fields")
@Entity
public class ResponseField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long responseId;
    private String name;
    private String type;
    private Long fatherId;
    private Long depth;
}
