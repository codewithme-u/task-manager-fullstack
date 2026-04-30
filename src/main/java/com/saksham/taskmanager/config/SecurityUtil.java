package com.saksham.taskmanager.config;

import org.springframework.stereotype.Component;

import com.saksham.taskmanager.entity.User;
import com.saksham.taskmanager.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserService userService;

    public User getCurrentUser(HttpServletRequest request) {
        String userId = request.getHeader("userId");

        if (userId == null) {
            throw new RuntimeException("Unauthorized");
        }

        return userService.getById(Long.parseLong(userId));
    }
}