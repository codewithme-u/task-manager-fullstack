package com.saksham.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saksham.taskmanager.entity.Project;
import com.saksham.taskmanager.entity.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByMembersContaining(User user);
}