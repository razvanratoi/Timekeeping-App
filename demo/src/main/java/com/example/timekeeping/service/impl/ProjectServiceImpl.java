package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.Client;
import com.example.timekeeping.model.EmployeeProjects;
import com.example.timekeeping.model.Project;
import com.example.timekeeping.repo.ClientRepo;
import com.example.timekeeping.repo.EmployeeProjectsRepo;
import com.example.timekeeping.repo.ProjectRepo;
import com.example.timekeeping.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final EmployeeProjectsRepo employeeProjectsRepo;
    private final ClientRepo clientRepo;

    @Override
    public Project create(Project project, String clientName) {
        Long id = (long) employeeProjectsRepo.findAll().size();
        project.setId((long) (projectRepo.findAll().size() + 1));
        projectRepo.save(project);
        employeeProjectsRepo.save(new EmployeeProjects(id + 1, project.getProjectManager(), project.getId()));
        return project;
    }

    @Override
    public Collection<Project> list() {
        return projectRepo.findAll().stream().toList();
    }


    @Override
    public Project get(Long id) {
        return projectRepo.findById(id).get();
    }

    @Override
    public Project update(Project Project) {
        return projectRepo.save(Project);
    }

    @Override
    public Boolean delete(Long id) {
        projectRepo.deleteById(id);
        return true;
    }
}
