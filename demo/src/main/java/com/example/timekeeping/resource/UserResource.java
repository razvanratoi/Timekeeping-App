package com.example.timekeeping.resource;

import com.example.timekeeping.model.*;
import com.example.timekeeping.service.impl.EmployeeServiceImpl;
import com.example.timekeeping.service.impl.LoggedHourServiceImpl;
import com.example.timekeeping.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserServiceImpl userService;
    private final EmployeeServiceImpl employeeService;

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list")
    public ResponseEntity<Response> getUsers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<User>) userService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getUser(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<User>) userService.get(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @PostMapping("/save")
    public ResponseEntity<Response> saveUser(@RequestBody User user){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data((List<?>) userService.create(user))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(Collections.singletonList(userService.delete(id)))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/check/{username}/{password}")
    public ResponseEntity<Response> checkUser(@ PathVariable String username, @PathVariable String password){
        Long userId = 0L;
        List<User> users = (List<User>) userService.list();
        for(User user : users){
            if(user.getUserName().equals(username) && user.getPassword().equals(password))
                userId = user.getUserId();
        }
        System.out.println(userId);
        Employee employee = employeeService.get(userId);
        System.out.println(employee.getRoleId());
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(List.of(userId, employee.getRoleId()))
                        .reason("motiv")
                        .build()
        );
    }
}
