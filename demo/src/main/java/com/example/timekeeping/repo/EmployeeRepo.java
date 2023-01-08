package com.example.timekeeping.repo;

import com.example.timekeeping.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Collection<Employee> findByRoleId(Long id);
}
