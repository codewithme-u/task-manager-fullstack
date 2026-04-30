package com.saksham.taskmanager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.saksham.taskmanager.entity.Project;
import com.saksham.taskmanager.entity.User;
import com.saksham.taskmanager.repository.ProjectRepository;
import com.saksham.taskmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project createProject(Project project, Long creatorId) {

        if (project.getName() == null || project.getName().isBlank()) {
            throw new RuntimeException("Project name is required");
        }

        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        project.setCreatedBy(creator);

        Set<User> members = new HashSet<>();
        members.add(creator);

        project.setMembers(members);

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

  
    public List<Project> getUserProjects(User user) {
        return projectRepository.findByMembersContaining(user);
    }

    public Project addMember(Long projectId, Long userId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (project.getMembers().contains(user)) {
            throw new RuntimeException("User already in project");
        }

        project.getMembers().add(user);

        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project updatedProject) {

        Project existing = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (updatedProject.getName() != null && !updatedProject.getName().isBlank()) {
            existing.setName(updatedProject.getName());
        }

        if (updatedProject.getDescription() != null) {
            existing.setDescription(updatedProject.getDescription());
        }

        return projectRepository.save(existing);
    }

    public void deleteProject(Long projectId) {

        if (!projectRepository.existsById(projectId)) {
            throw new RuntimeException("Project not found");
        }

        projectRepository.deleteById(projectId);
    }
}