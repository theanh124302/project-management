package com.example.projectmanagement.model.auth.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    private String name;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
}