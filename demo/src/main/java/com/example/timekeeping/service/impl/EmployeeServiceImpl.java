package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.Employee;
import com.example.timekeeping.repo.EmployeeRepo;
import com.example.timekeeping.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee create(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Collection<Employee> list() {
        return employeeRepo.findAll().stream().toList();
    }

    @Override
    public Employee get(Long id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Boolean delete(Long id) {
        employeeRepo.deleteById(id);
        return true;
    }

    public Collection<Employee> getpM() {
        return employeeRepo.findByRoleId(2L);
    }
}
