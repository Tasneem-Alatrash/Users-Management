package com.example.SpringBoot.Service;

import com.example.SpringBoot.DTOs.Request.CreateAddressRequest;
import com.example.SpringBoot.DTOs.Response.AddressResponse;
import com.example.SpringBoot.Model.Address;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Repository.AddressRepository;
import com.example.SpringBoot.Repository.UserRepository;
import com.example.SpringBoot.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public AddressResponse addAddress(CreateAddressRequest request){
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(()-> new UserNotFoundException("User with id " + request.getUser_id() + " not found"));
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setUser(user);

        addressRepository.save(address);
        return toResponse(address);
    }

    public List<AddressResponse> getAddressByUser(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User with id " + userId + " not found"));
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    private AddressResponse toResponse(Address address){
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setStreet(address.getStreet());
        response.setCity(address.getCity());
        response.setUser_id(address.getUser().getId());
        return response;
    }
}
