package com.example.timekeeping.resource;

import com.example.timekeeping.model.EmployeeProjects;
import com.example.timekeeping.model.Project;
import com.example.timekeeping.model.Response;
import com.example.timekeeping.model.SimpleResponse;
import com.example.timekeeping.repo.EmployeeProjectsRepo;
import com.example.timekeeping.service.impl.EmployeeServiceImpl;
import com.example.timekeeping.service.impl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectResource {


    private final ProjectServiceImpl projectService;
    private final EmployeeServiceImpl employeeService;
    private final EmployeeProjectsRepo employeeProjectsRepo;

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list")
    public ResponseEntity<Response> getProjects(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)  projectService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list/{id}")
    public ResponseEntity<Response> getProjectsByEmployee(@PathVariable Long id){
        List<EmployeeProjects> employeeProjects = (List<EmployeeProjects>) employeeProjectsRepo.findByEmployeeId(id);
        List<Project> projects = new ArrayList<>();
        for(EmployeeProjects ep : employeeProjects){
            projects.add(projectService.get(ep.getProjectId()));
        }
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(projects)
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<SimpleResponse> getProject(@PathVariable Long id){
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(projectService.get(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<Response> saveProject(@RequestBody Project Project){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(List.of( projectService.create(Project, "client")))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProject(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(Collections.singletonList(projectService.delete(id)))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/team")
    public ResponseEntity<Response> addToTeam(@RequestBody EmployeeProjects employeeProjects){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(List.of(employeeProjectsRepo.save(employeeProjects)))
                        .reason("motiv")
                        .build()
        );
    }
}
