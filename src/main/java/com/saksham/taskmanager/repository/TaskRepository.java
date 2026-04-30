package com.saksham.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saksham.taskmanager.entity.Task;
import com.saksham.taskmanager.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedTo(User user);

    List<Task> findByProjectId(Long projectId);
}