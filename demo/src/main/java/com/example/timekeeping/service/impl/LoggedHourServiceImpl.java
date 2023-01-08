package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.LoggedHour;
import com.example.timekeeping.repo.LoggedHourRepo;
import com.example.timekeeping.service.LoggedHourService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class LoggedHourServiceImpl implements LoggedHourService {

    private final LoggedHourRepo loggedHourRepo;

    @Override
    public LoggedHour create(LoggedHour loggedHour) {
        return loggedHourRepo.save(loggedHour);
    }

    @Override
    public Collection<LoggedHour> list() {
        return loggedHourRepo.findAll().stream().toList();
    }

    @Override
    public Collection<LoggedHour> listByEmployee(Long id) {
        return loggedHourRepo.findByEmployeeId(id);
    }

    @Override
    public LoggedHour get(Long id) {
        return loggedHourRepo.findById(id).get();
    }

    @Override
    public LoggedHour update(LoggedHour loggedHour) {
        return loggedHourRepo.save(loggedHour);
    }

    @Override
    public Boolean delete(Long id) {
        loggedHourRepo.deleteById(id);
        return true;
    }


}
