package com.example.timekeeping.resource;

import com.example.timekeeping.model.*;
import com.example.timekeeping.repo.EmployeeProjectsRepo;
import com.example.timekeeping.service.impl.EmployeeServiceImpl;
import com.example.timekeeping.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeResource {

    private final EmployeeServiceImpl employeeService;
    private final EmployeeProjectsRepo employeeProjects;
    private final UserServiceImpl userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<Response> getEmployees(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<Employee>) employeeService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getTeam/{id}")
    public ResponseEntity<Response> getEmployeesByProject(@PathVariable Long id){
        List<EmployeeProjects> employeeProjectsList = (List<EmployeeProjects>) employeeProjects.findByProjectId(id);
        List<Employee> team = new ArrayList<>();
        for(EmployeeProjects ep : employeeProjectsList){
            team.add(employeeService.get(ep.getEmployeeId()));
        }
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(team)
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getNoTeam/{id}")
    public ResponseEntity<Response> getFreeEmployees(@PathVariable Long id){
        List<EmployeeProjects> employeeProjectsList = (List<EmployeeProjects>) employeeProjects.findByProjectId(id);
        System.out.println(employeeProjectsList);
        List<Long> employeeIds = employeeProjectsList.stream().map(EmployeeProjects::getEmployeeId).toList();
        System.out.println(employeeIds);
        List<Employee> all = (List<Employee>) employeeService.list();
        System.out.println(all);
        all = all.stream().filter(e -> !employeeIds.contains(e.getId())).toList();
        System.out.println(all);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(all)
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<SimpleResponse> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(employeeService.get(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<Response> saveEmployee(@RequestBody Employee employee){
        employee.setHireDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        employee.setId(null);
        employee.setRoleId(4L);
        employeeService.create(employee);
        Long id = (long) (userService.list().size() + 1);
        System.out.println(id);
        String[] names = employee.getName().split(" ");
        String username = names[0].substring(0, 1).toLowerCase() + names[1].toLowerCase();
        String password = "parola";
        User user = new User(null, username, password, employee.getId());
        userService.create(user);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(List.of(employee))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update")
    public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data((List<?>) employeeService.create(employee))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(Collections.singletonList(employeeService.delete(id)))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getPM")
    public ResponseEntity<SimpleResponse> getPM(){
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(employeeService.getpM())
                        .reason("motiv")
                        .build()
        );
    }
}
