package com.example.timekeeping.resource;

import com.example.timekeeping.model.LoggedHour;
import com.example.timekeeping.model.Response;
import com.example.timekeeping.model.Task;
import com.example.timekeeping.service.impl.LoggedHourServiceImpl;
import com.example.timekeeping.service.impl.TaskServiceImpl;
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
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskResource {

    private final TaskServiceImpl taskService;

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list")
    public ResponseEntity<Response> getTasks(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)  taskService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list/{id}")
    public ResponseEntity<Response> getTasksByProject(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)  taskService.listByProject(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getTask(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)  taskService.get(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<Response> saveTask(@RequestBody Task task){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data((List<?>)  taskService.create(task))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteTask(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(Collections.singletonList(taskService.delete(id)))
                        .reason("motiv")
                        .build()
        );
    }
}
