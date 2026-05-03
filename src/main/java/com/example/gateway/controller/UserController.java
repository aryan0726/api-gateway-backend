package com.example.gateway.controller;

import com.example.gateway.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<ApiResponse> getUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Hello, " + username + "! This is a USER-protected endpoint.")
                .build());
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Profile of: " + auth.getName() + " | Roles: " + auth.getAuthorities())
                .build());
    }
}
