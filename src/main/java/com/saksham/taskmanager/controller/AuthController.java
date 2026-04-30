package com.saksham.taskmanager.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saksham.taskmanager.entity.User;
import com.saksham.taskmanager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.registerUser(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {

        Optional<User> user = userService.login(
                loginUser.getEmail(),
                loginUser.getPassword()
        );

        if (user.isPresent()
                && passwordEncoder.matches(loginUser.getPassword(), user.get().getPassword())) {

            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.badRequest()
                .body(Map.of("message", "Invalid credentials"));
    }
}