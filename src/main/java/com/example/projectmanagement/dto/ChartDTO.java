package com.example.projectmanagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChartDTO {
    private String name;
    private Long value;
    private Double percentage;
}
