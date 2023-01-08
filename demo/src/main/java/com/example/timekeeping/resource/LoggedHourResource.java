package com.example.timekeeping.resource;

import com.example.timekeeping.model.Client;
import com.example.timekeeping.model.LoggedHour;
import com.example.timekeeping.model.Response;
import com.example.timekeeping.model.SimpleResponse;
import com.example.timekeeping.service.impl.LoggedHourServiceImpl;
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
@RequestMapping("/loggedhour")
@RequiredArgsConstructor
public class LoggedHourResource {

    private final LoggedHourServiceImpl loggedHourService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list/{id}")
    public ResponseEntity<Response> getLoggedHours(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)  loggedHourService.listByEmployee(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<Response> getLoggedHoursAll(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)  loggedHourService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<SimpleResponse> getLoggedHour(@PathVariable Long id){
        LoggedHour l = loggedHourService.get(id);
        System.out.println(l);
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(loggedHourService.get(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveLoggedHour(@RequestBody LoggedHour loggedHour){
        Long id = (long) loggedHourService.list().size();
        loggedHour.setId(id + 1);
        System.out.println(loggedHour);
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(loggedHourService.create(loggedHour))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteLoggedHour(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(Collections.singletonList(loggedHourService.delete(id)))
                        .reason("motiv")
                        .build()
        );
    }
}
