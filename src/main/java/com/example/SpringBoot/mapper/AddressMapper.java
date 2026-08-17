package com.example.SpringBoot.mapper;

import com.example.SpringBoot.DTOs.Response.AddressDto;
import com.example.SpringBoot.Model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDto toResponse(Address address){
        AddressDto response = new AddressDto();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setUserId(address.getUser().getId());
        return response;
    }
}
