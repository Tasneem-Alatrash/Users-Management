package com.example.SpringBoot.DTOs.Response;

import lombok.Data;

@Data
public class AddressResponse {

    private Integer id;

    private String street;

    private String city;

    private Integer user_id;
}
