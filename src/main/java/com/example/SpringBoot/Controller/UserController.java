package com.example.SpringBoot.Controller;

import com.example.SpringBoot.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/Test")
    public ResponseEntity<String> CountUser(){
        boolean test = service.CountUser();
        if (test == true){
            return ResponseEntity.ok("Database is working ");
        }else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Database is down ");
        }
    }
}
