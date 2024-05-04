package com.example.projectmanagement.controller;
import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.service.UserService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<UserDTO>> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.update(userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(ResponseTemplate.<UserDTO>builder()
                    .status(HttpStatus.OK)
                    .message("User updated successfully")
                    .data(updatedUser)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<UserDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("User not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<UserDTO>> deleteUser(@RequestBody UserDTO userDTO) {
        UserDTO deletedUser = userService.delete(userDTO);
        if (deletedUser != null) {
            return ResponseEntity.ok(ResponseTemplate.<UserDTO>builder()
                    .status(HttpStatus.OK)
                    .message("User deleted successfully")
                    .data(deletedUser)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<UserDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("User not found")
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(ResponseTemplate.<List<UserDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .data(users)
                .build());
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<ResponseTemplate<UserDTO>> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(ResponseTemplate.<UserDTO>builder()
                    .status(HttpStatus.OK)
                    .message("User found successfully")
                    .data(user)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<UserDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("User not found")
                    .build());
        }
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<ResponseTemplate<List<UserDTO>>> getUsersByEmail(@PathVariable String email) {
        List<UserDTO> users = userService.findByEmail(email);
        return ResponseEntity.ok(ResponseTemplate.<List<UserDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .data(users)
                .build());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ResponseTemplate<List<UserDTO>>> getUsersByName(@PathVariable String name) {
        List<UserDTO> users = userService.findByName(name);
        return ResponseEntity.ok(ResponseTemplate.<List<UserDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .data(users)
                .build());
    }
}
