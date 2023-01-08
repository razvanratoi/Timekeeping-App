package com.example.timekeeping.repo;

import com.example.timekeeping.model.FreeDaysRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FreeDaysRequestRepo extends JpaRepository<FreeDaysRequest, Long> {
    Collection<FreeDaysRequest> findByEmployeeId(Long id);
}
