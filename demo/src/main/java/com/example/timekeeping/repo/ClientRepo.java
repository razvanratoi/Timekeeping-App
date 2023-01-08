package com.example.timekeeping.repo;

import com.example.timekeeping.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
