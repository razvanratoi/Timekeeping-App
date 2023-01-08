package com.example.timekeeping.resource;

import com.example.timekeeping.model.Client;
import com.example.timekeeping.model.Response;
import com.example.timekeeping.model.SimpleResponse;
import com.example.timekeeping.service.impl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {
    private final ClientServiceImpl clientService;

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/list")
    public ResponseEntity<Response> getClients(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data((List<?>)  clientService.list())
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @PostMapping("/save")
    public ResponseEntity<Response> saveClient(@RequestBody Client client){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data((List<?>) clientService.create(client))
                        .reason("motiv")
                        .build()
        );
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/get/{id}")
    public ResponseEntity<SimpleResponse> getClient(@PathVariable Long id){
        return ResponseEntity.ok(
                SimpleResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .msg("a mers")
                        .devMsg("")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(clientService.get(id))
                        .reason("motiv")
                        .build()
        );
    }
}
