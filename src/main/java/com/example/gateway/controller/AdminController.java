package com.example.gateway.controller;

import com.example.gateway.dto.ApiResponse;
import com.example.gateway.entity.User;
import com.example.gateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<ApiResponse> getAdminData() {
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Welcome, Admin! This is an ADMIN-only protected endpoint.")
                .build());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.stream()
                .map(u -> Map.of(
                        "id", u.getId(),
                        "username", u.getUsername(),
                        "role", u.getRole().name()
                ))
                .toList());
    }
}
