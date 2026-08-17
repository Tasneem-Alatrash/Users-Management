package com.example.SpringBoot.mapper;

import com.example.SpringBoot.DTOs.Response.AddressDto;
import com.example.SpringBoot.DTOs.Response.UserResponse;
import com.example.SpringBoot.DTOs.Response.UserWithDetailsDto;
import com.example.SpringBoot.Model.Address;
import com.example.SpringBoot.Model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setFirstName(user.getFirstName());

        response.setLastName(user.getLastName());

        response.setPhoneNumber(user.getPhoneNumber());

        return response;
    }

    public UserWithDetailsDto toResponseWithAddresses(User user) {
        UserWithDetailsDto response = new UserWithDetailsDto();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());


        List<AddressDto> addresses = user.getAddresses().stream()
                .map(this::toAddressResponse)
                .toList();
        response.setAddresses(addresses);

        return response;
    }

    private AddressDto toAddressResponse(Address address) {
        AddressDto response = new AddressDto();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setUserId(address.getUser().getId());
        return response;
    }
}
