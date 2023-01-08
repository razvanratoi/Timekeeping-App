package com.example.timekeeping.service;

import com.example.timekeeping.model.LoggedHour;

import java.util.Collection;

public interface LoggedHourService {
    LoggedHour create(LoggedHour LoggedHour);
    Collection<LoggedHour> list();

    Collection<LoggedHour> listByEmployee(Long id);
    LoggedHour get(Long id);
    LoggedHour update(LoggedHour LoggedHour);
    Boolean delete(Long id);
}
