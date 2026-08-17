package com.example.SpringBoot.DTOs.Response;

import lombok.Data;

import java.util.List;

@Data
public class UserWithAddressResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private List<AddressResponse> addresses;
}
