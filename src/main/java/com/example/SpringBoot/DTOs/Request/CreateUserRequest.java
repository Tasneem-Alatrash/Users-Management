package com.example.SpringBoot.DTOs.Request;

import lombok.Data;
import lombok.ToString;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ToString.Exclude
    private String password;
}
