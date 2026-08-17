package com.example.SpringBoot.DTOs.Response;

import lombok.Data;

import java.util.List;

@Data
public class UserWithDetailsDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private List<AddressDto> addresses;
}
