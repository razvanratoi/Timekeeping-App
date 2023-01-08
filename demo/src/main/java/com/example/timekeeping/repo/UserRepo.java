package com.example.timekeeping.repo;

import com.example.timekeeping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
