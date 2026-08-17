package com.example.SpringBoot.mapper;

import com.example.SpringBoot.DTOs.Response.AddressResponse;
import com.example.SpringBoot.DTOs.Response.UserResponse;
import com.example.SpringBoot.DTOs.Response.UserWithAddressResponse;
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

    public UserWithAddressResponse toResponseWithAddresses(User user) {
        UserWithAddressResponse response = new UserWithAddressResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());


        List<AddressResponse> addresses = user.getAddresses().stream()
                .map(this::toAddressResponse)
                .toList();
        response.setAddresses(addresses);

        return response;
    }

    private AddressResponse toAddressResponse(Address address) {
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setUserId(address.getUser().getId());
        return response;
    }
}
