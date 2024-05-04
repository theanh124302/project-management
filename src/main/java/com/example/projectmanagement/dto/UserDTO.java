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
public class UserDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long age;
    private String email;
    private String username;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
