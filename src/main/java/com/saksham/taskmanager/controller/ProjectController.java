package com.saksham.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.saksham.taskmanager.config.SecurityUtil;
import com.saksham.taskmanager.entity.Project;
import com.saksham.taskmanager.entity.Role;
import com.saksham.taskmanager.entity.User;
import com.saksham.taskmanager.service.ProjectService;
import com.saksham.taskmanager.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final SecurityUtil securityUtil;

    @PostMapping
    public Project createProject(@RequestBody Project project,
                                 HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        if (current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can create project");
        }

        return projectService.createProject(project, current.getId());
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{userId}")
    public List<Project> getUserProjects(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return projectService.getUserProjects(user);
    }

    @PostMapping("/{projectId}/add-member")
    public Project addMember(@PathVariable Long projectId,
                             @RequestParam Long userId,
                             HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        if (current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can add members");
        }

        return projectService.addMember(projectId, userId);
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId,
                                 @RequestBody Project updatedProject) {
        return projectService.updateProject(projectId, updatedProject);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId,
                              HttpServletRequest request) {

        User current = securityUtil.getCurrentUser(request);

        if (current.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can delete project");
        }

        projectService.deleteProject(projectId);
    }
    
    
    
}