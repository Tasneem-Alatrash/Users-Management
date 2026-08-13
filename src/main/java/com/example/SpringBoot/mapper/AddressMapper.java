package com.example.SpringBoot.mapper;

import com.example.SpringBoot.DTOs.Response.AddressResponse;
import com.example.SpringBoot.Model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressResponse toResponse(Address address){
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setUserId(address.getUser().getId());
        return response;
    }
}
