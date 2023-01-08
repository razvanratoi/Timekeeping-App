package com.example.timekeeping.repo;

import com.example.timekeeping.model.LoggedHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LoggedHourRepo extends JpaRepository<LoggedHour, Long> {
    Collection<LoggedHour> findByEmployeeId(Long employeeId);
}
