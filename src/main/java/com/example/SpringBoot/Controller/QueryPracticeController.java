package com.example.SpringBoot.Controller;

import com.example.SpringBoot.DTOs.Response.UserResponse;
import com.example.SpringBoot.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.SpringBoot.Service.QueryPracticeService;

import java.util.List;

@RestController
@RequestMapping("/api/practice/queries")
public class QueryPracticeController {

    private final QueryPracticeService service;

    public QueryPracticeController(QueryPracticeService service) {
        this.service = service;
    }

    @GetMapping("/derived")
    public ResponseEntity<List<UserResponse>> derived(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok( service.derived(firstName, lastName));
    }

    @GetMapping("/jpql")
    public ResponseEntity<List<UserResponse>> jpql(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok(service.jpql(firstName, lastName));
    }

    @GetMapping("/native")
    public ResponseEntity<List<UserResponse>> nativeQuery(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.ok(service.nativeQuery(firstName, lastName));
    }
}
