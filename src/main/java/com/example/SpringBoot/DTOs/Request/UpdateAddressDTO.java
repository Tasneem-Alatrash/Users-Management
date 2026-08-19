package com.example.SpringBoot.DTOs.Request;

import lombok.Data;

@Data
public class UpdateAddressDTO {

    private String street;

    private String city;

    private String country;

}
