package com.example.timekeeping.service;

import com.example.timekeeping.model.FreeDaysRequest;

import java.util.Collection;

public interface FreeDaysRequestService {
    FreeDaysRequest create(FreeDaysRequest FreeDaysRequest);
    Collection<FreeDaysRequest> list();

    Collection<FreeDaysRequest> listByEmployee(Long id);
    FreeDaysRequest get(Long id);
    FreeDaysRequest update(FreeDaysRequest FreeDaysRequest);
    Boolean delete(Long id);
}
