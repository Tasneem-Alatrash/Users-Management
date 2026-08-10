package com.example.SpringBoot.Controller;

import com.example.SpringBoot.DTOs.Request.CreateUserRequest;
import com.example.SpringBoot.DTOs.Response.userResponse;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<userResponse>> GetAllUsers(){
        return ResponseEntity.ok(service.GetAllUsers());
    }

    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping
    public ResponseEntity<userResponse> createUser(@RequestBody CreateUserRequest request){
        userResponse response = service.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<userResponse> GetUserById(@PathVariable Integer id){
        return  ResponseEntity.ok(service.GetUserById(id));
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<userResponse>> findByFirstName(@RequestParam String firstName){
        return ResponseEntity.ok(service.findByFirstName(firstName));
    }

    @GetMapping("/search/phone")
    public ResponseEntity<userResponse> findByPhoneNumber(@RequestParam String phoneNumber){
        return ResponseEntity.ok(service.findByPhoneNumber(phoneNumber));
    }
}
