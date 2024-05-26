package com.example.projectmanagement.dto;

import com.example.projectmanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInProjectDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long age;
    private String email;
    private String username;
    private String avatar;
    private String role;
}
