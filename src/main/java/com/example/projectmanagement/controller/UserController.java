package com.example.projectmanagement.controller;
import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.dto.UserInProjectDTO;
import com.example.projectmanagement.service.UserService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
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

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseTemplate<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
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

    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<UserDTO>>> getAllUsers(Pageable pageable) {
        List<UserDTO> users = userService.getAllUsers(pageable);

        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        long totalItems = userService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return ResponseEntity.ok(ResponseTemplate.<List<UserDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(users)
                .build());
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<ResponseTemplate<List<UserDTO>>> getUsersByName(@PathVariable String name,Pageable pageable) {
        List<UserDTO> users = userService.findByName(name,pageable);
        System.out.println(users);
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        long totalItems = userService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<UserDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(users)
                .build());
    }

    @GetMapping("/findByUsername/{username}")
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

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<ResponseTemplate<UserDTO>> getUsersByEmail(@PathVariable String email) {
        UserDTO user = userService.findByEmail(email);
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

    @GetMapping("/findByPhoneNumber/{phoneNumber}")
    public ResponseEntity<ResponseTemplate<UserDTO>> getUsersByPhoneNumber(@PathVariable String phoneNumber) {
        UserDTO user = userService.findByPhoneNumber(phoneNumber);
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

    @GetMapping("/findByEmailOrUsername/{email}/{username}")
    public ResponseEntity<ResponseTemplate<UserDTO>> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username) {
        UserDTO user = userService.findByEmailOrUsername(email, username);
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

    @GetMapping("/findByProjectId/{projectId}")
    public ResponseEntity<ResponseTemplate<List<UserInProjectDTO>>> findUsersByProjectId(@PathVariable Long projectId, Pageable pageable) {
        List<UserInProjectDTO> users = userService.findByProjectId(projectId, pageable);
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        long totalItems = userService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<UserInProjectDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(users)
                .build());
    }

    @GetMapping("/findByTaskId/{taskId}")
    public ResponseEntity<ResponseTemplate<List<UserDTO>>> findUsersByTaskId(@PathVariable Long taskId, Pageable pageable) {
        List<UserDTO> users = userService.findByTaskId(taskId, pageable);
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        long totalItems = userService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<UserDTO>>builder()
                .status(HttpStatus.OK)
                .message("Users found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(users)
                .build());
    }


}
