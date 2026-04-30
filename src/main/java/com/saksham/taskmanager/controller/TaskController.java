package com.saksham.taskmanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.saksham.taskmanager.config.SecurityUtil;
import com.saksham.taskmanager.entity.*;
import com.saksham.taskmanager.service.TaskService;
import com.saksham.taskmanager.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final SecurityUtil securityUtil;

    @PostMapping
    public Task createTask(@RequestBody Task task,
                           @RequestParam Long projectId,
                           @RequestParam Long userId,
                           HttpServletRequest request) {

    	User current = securityUtil.getCurrentUser(request);

        if (current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can assign tasks");
        }

        return taskService.createTask(task, projectId, userId);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getUserTasks(@PathVariable Long userId,
                                  HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        if (!current.getId().equals(userId) && current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Unauthorized");
        }

        User user = userService.getById(userId);
        return taskService.getUserTasks(user);
    }

    @PutMapping("/{taskId}")
    public Task updateStatus(@PathVariable Long taskId,
                             @RequestParam Status status,
                             HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        Task task = taskService.getById(taskId);

        if (!task.getAssignedTo().getId().equals(current.getId())) {
            throw new RuntimeException("You can only update your own task");
        }

        return taskService.updateStatus(taskId, status);
    }

    @GetMapping("/overdue/{userId}")
    public List<Task> getUserOverdueTasks(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return taskService.getOverdueTasksByUser(user);
    }
    
    @GetMapping("/project/{projectId}")
    public List<Task> getProjectTasks(@PathVariable Long projectId,
                                     HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);


        return taskService.getTasksByProject(projectId);
    }
    @GetMapping("/tasks-per-user")
    public Map<String, Long> getTasksPerUser(HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        if (current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin allowed");
        }

        return taskService.getTaskCountPerUser();
    }
    @GetMapping("/all")
    public List<Task> getAllTasks(HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        if (current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can view all tasks");
        }

        return taskService.getAllTasks();
    }
    
}