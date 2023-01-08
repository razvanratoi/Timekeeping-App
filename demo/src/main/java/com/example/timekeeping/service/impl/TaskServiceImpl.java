package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.Task;
import com.example.timekeeping.repo.TaskRepo;
import com.example.timekeeping.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Override
    public Task create(Task Task) {
        long size = taskRepo.findAll().size();
        Task.setId(size + 1);
        return taskRepo.save(Task);
    }

    @Override
    public Collection<Task> list() {
        return taskRepo.findAll().stream().toList();
    }

    @Override
    public Task get(Long id) {
        return taskRepo.findById(id).get();
    }

    @Override
    public Task update(Task Task) {
        return taskRepo.save(Task);
    }

    @Override
    public Boolean delete(Long id) {
        taskRepo.deleteById(id);
        return true;
    }

    public Collection<Task> listByProject(Long id) {
        return taskRepo.findByProjectId(id);
    }
}
