package com.example.SpringBoot.Service;

import com.example.SpringBoot.DTOs.Request.CreateUserRequest;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Repository.UserRepository;
import com.example.SpringBoot.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import com.example.SpringBoot.DTOs.Response.userResponse;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public long CountUser(){
        return userRepository.count();
    }
    public userResponse createUser(CreateUserRequest request){
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getPassword()
        );
        userRepository.save(user);
        return toResponse(user);
    }
    public List<userResponse> GetAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public userResponse GetUserById(Integer id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

        return toResponse(user);

    }
    public List<userResponse> findByFirstName(String firstName){

        return userRepository.findByFirstName(firstName)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public userResponse findByPhoneNumber(String phoneNumber){
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("No user found with phone number: " + phoneNumber));

        return toResponse(user);
    }
    private userResponse toResponse(User user) {
        userResponse response = new userResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        return response;
    }
}

