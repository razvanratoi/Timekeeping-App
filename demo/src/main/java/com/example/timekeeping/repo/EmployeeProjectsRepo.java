package com.example.timekeeping.repo;

import com.example.timekeeping.model.EmployeeProjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EmployeeProjectsRepo extends JpaRepository<EmployeeProjects, Long> {
    Collection<EmployeeProjects> findByEmployeeId(Long id);
    Collection<EmployeeProjects> findByProjectId(Long id);
}
