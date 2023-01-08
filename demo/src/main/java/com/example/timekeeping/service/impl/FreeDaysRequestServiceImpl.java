package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.Employee;
import com.example.timekeeping.model.FreeDaysRequest;
import com.example.timekeeping.model.LoggedHour;
import com.example.timekeeping.repo.FreeDaysRequestRepo;
import com.example.timekeeping.service.FreeDaysRequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class FreeDaysRequestServiceImpl implements FreeDaysRequestService {
    private final FreeDaysRequestRepo freeDaysRequestRepo;

    @Override
    public FreeDaysRequest create(FreeDaysRequest freeDaysRequest) {
        freeDaysRequest.setStatus("Pending");
        return freeDaysRequestRepo.save(freeDaysRequest);
    }

    @Override
    public Collection<FreeDaysRequest> list() {
        return freeDaysRequestRepo.findAll().stream().toList();
    }

    @Override
    public Collection<FreeDaysRequest> listByEmployee(Long id) {
        return freeDaysRequestRepo.findByEmployeeId(id);
    }

    @Override
    public FreeDaysRequest get(Long id) {
        return freeDaysRequestRepo.findById(id).get();
    }

    @Override
    public FreeDaysRequest update(FreeDaysRequest freeDaysRequest) {
        return freeDaysRequestRepo.save(freeDaysRequest);
    }

    @Override
    public Boolean delete(Long id) {
        freeDaysRequestRepo.deleteById(id);
        return true;
    }
}
