package com.example.timekeeping.service;

import com.example.timekeeping.model.Project;

import java.util.Collection;

public interface ProjectService {
    Project create(Project Project, String clientName);
    Collection<Project> list();
    Project get(Long id);
    Project update(Project Project);
    Boolean delete(Long id);
}
