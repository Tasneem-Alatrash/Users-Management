package com.example.SpringBoot.Controller;

import com.example.SpringBoot.DTOs.Request.CreateUserRequest;
import com.example.SpringBoot.DTOs.Request.UserSearch;
import com.example.SpringBoot.DTOs.Response.UserResponse;
import com.example.SpringBoot.DTOs.Response.UserWithDetailsDto;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Service.UserService;
import com.example.SpringBoot.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request){
        UserResponse response = service.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id){
        return  ResponseEntity.ok(service.getUserById(id));
    }

    // criteria Pagination
    @GetMapping("/search-criteria")
    public ResponseEntity<Page<UserResponse>> searchUsers( UserSearch request , Pageable pageable){
        return ResponseEntity.ok(service.searchUser(request, pageable)
                .map(mapper::toResponse));
    }

    // simple Pagination
    @GetMapping("/paginated")
    public Page<UserResponse> getUserPaginated(Pageable pageable){
        return service.getUserPaginated(pageable)
                .map(mapper::toResponse);
    }

    // JPQL Pagination
    @GetMapping("/paginated-jpql")
    public Page<UserResponse> getAllUsersJpqlPaginated(Pageable pageable){
        return service.getAllUsersJpqlPaginated(pageable)
                .map(mapper::toResponse);
    }

    // Native Pagination
    @GetMapping("/paginated-native")
    public Page<UserResponse> getAllUsersNativePaginated(Pageable pageable){
        return service.getAllUsersNativePaginated(pageable)
                .map(mapper::toResponse);
    }

    @GetMapping("/with-addresses-paginated")
    public Page<UserWithDetailsDto> getUsersWithAddresses(Pageable pageable) {
        Page<User> users = service.getUsersWithAddressesPaginated(pageable);
        return users.map(mapper::toResponseWithAddresses);
    }
}
