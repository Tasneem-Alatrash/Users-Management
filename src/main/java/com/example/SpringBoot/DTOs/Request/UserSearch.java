package com.example.SpringBoot.DTOs.Request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserSearch {

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
