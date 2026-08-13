package com.example.SpringBoot.DTOs.Request;

import lombok.Data;

@Data
public class CreateAddressRequest {

    private String street;

    private String city;

    private Integer userId;
}
