package com.data.controller;

import com.data.model.dto.request.LoginRequest;
import com.data.model.dto.request.RegisterRequest;
import com.data.model.dto.response.APIResponse;
import com.data.model.dto.response.JWTResponse;
import com.data.model.entity.User;
import com.data.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<User>> register(@RequestBody RegisterRequest user) {
        User registeredUser = authService.register(user);
        APIResponse<User> response = new APIResponse<>("User registered successfully", registeredUser, true, 201);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginRequest user) {
        JWTResponse jwtResponse = authService.login(user);
        APIResponse<String> response = new APIResponse<>("Login successful", jwtResponse.getToken(), true, 200);
        return ResponseEntity.ok(response);
    }
}
