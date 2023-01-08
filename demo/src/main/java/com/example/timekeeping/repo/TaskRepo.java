package com.example.timekeeping.repo;

import com.example.timekeeping.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TaskRepo extends JpaRepository<Task, Long> {
    public Collection<Task> findByProjectId(Long id);
}
