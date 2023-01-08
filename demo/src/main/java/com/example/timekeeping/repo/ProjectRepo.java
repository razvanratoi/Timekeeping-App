package com.example.timekeeping.repo;

import com.example.timekeeping.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
