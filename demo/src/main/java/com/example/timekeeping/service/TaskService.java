package com.example.timekeeping.service;

import com.example.timekeeping.model.Task;

import java.util.Collection;

public interface TaskService {
    Task create(Task Task);
    Collection<Task> list();
    Task get(Long id);
    Task update(Task Task);
    Boolean delete(Long id);
}
