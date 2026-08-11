package com.example.SpringBoot.mapper;

import com.example.SpringBoot.DTOs.Response.UserResponse;
import com.example.SpringBoot.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setFirstName(user.getFirstName());

        response.setLastName(user.getLastName());

        response.setPhoneNumber(user.getPhoneNumber());

        return response;
    }
}
