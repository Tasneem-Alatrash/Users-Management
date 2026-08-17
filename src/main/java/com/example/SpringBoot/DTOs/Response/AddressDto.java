package com.example.SpringBoot.DTOs.Response;

import lombok.Data;

@Data
public class AddressDto {

    private Integer id;

    private String street;

    private String city;

    private Integer userId;
}
