package com.example.timekeeping.resource;

import com.example.timekeeping.model.FreeDaysRequest;
import com.example.timekeeping.model.LoggedHour;
import com.example.timekeeping.model.Response;
import com.example.timekeeping.model.SimpleResponse;
import com.example.timekeeping.service.impl.FreeDaysRequestServiceImpl;
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
@RequestMapping("/freedaysrequest")
@RequiredArgsConstructor
public class FreeDaysRequestResource {

    private final FreeDaysRequestServiceImpl freeDaysRequestService;

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list/{id}")
    public ResponseEntity<Response> getFreeDays(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)freeDaysRequestService.listByEmployee(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public ResponseEntity<Response> getFreeDaysAll(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data((List<?>)freeDaysRequestService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<SimpleResponse> getFreeDay(@PathVariable Long id){
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(freeDaysRequestService.get(id))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<Response> saveFreeDay(@RequestBody FreeDaysRequest freeDaysRequest){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(List.of(freeDaysRequestService.create(freeDaysRequest)))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update")
    public ResponseEntity<SimpleResponse> updateFreeDay(@RequestBody FreeDaysRequest freeDaysRequest){
        System.out.println("updating " + freeDaysRequest.getStatus());
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(freeDaysRequestService.update(freeDaysRequest))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteFreeDay(@PathVariable Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(OK.CREATED)
                        .statusCode(OK.value())
                        .data(Collections.singletonList(freeDaysRequestService.delete(id)))
                        .reason("motiv")
                        .build()
        );
    }
}
