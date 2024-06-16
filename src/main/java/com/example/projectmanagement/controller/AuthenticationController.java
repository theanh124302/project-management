package com.example.projectmanagement.controller;


import com.example.projectmanagement.model.auth.response.AuthenticationResponseDTO;
import com.example.projectmanagement.model.auth.request.RefreshTokenRequestDTO;
import com.example.projectmanagement.model.auth.request.SignInRequestDTO;
import com.example.projectmanagement.model.auth.request.SignUpRequestDTO;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.service.AuthenticationService;
import com.example.projectmanagement.service.ProjectService;
import com.example.projectmanagement.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ProjectService projectService;
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequestDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO){
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponseDTO> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDTO));
    }

    @GetMapping("/checkValidUser")
    public ResponseEntity<ResponseTemplate<Boolean>> checkValidUser(@RequestParam Long projectId, @RequestParam Long userId) {
        boolean isValid = projectService.checkValidUser(projectId, userId);
        return ResponseEntity.ok(ResponseTemplate.<Boolean>builder()
                .status(HttpStatus.OK)
                .message("User is valid")
                .data(isValid)
                .build());
    }
}


