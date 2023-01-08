package com.example.timekeeping.service;

import com.example.timekeeping.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee create(Employee Employee);
    Collection<Employee> list();
    Employee get(Long id);
    Employee update(Employee Employee);
    Boolean delete(Long id);
    
}
