package com.example.SpringBoot.Service;

import com.example.SpringBoot.DTOs.Request.CreateUserRequest;
import com.example.SpringBoot.DTOs.Request.UserSearch;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Repository.UserRepository;
import com.example.SpringBoot.exceptions.UserNotFoundException;
import com.example.SpringBoot.mapper.UserMapper;
import org.springframework.data.jpa.domain.Specification;
import static com.example.SpringBoot.specification.UserSpecification.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.SpringBoot.DTOs.Response.UserResponse;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper mapper) {

        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public long countUser(){
        return userRepository.count();
    }

    public UserResponse createUser(CreateUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return mapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UserResponse getUserById(Integer id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        return mapper.toResponse(user);

    }

    public List<UserResponse> searchUser(UserSearch request){
        Specification<User> spec = Specification
                .where(hasFirstName(request.getFirstName()))
                .and(hasLastName(request.getLastName()))
                .and(hasPhoneNumber(request.getPhoneNumber()));
        return userRepository.findAll(spec)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public User getUserEntityById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

}

