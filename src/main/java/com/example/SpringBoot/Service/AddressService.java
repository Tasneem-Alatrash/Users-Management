package com.example.SpringBoot.Service;

import com.example.SpringBoot.DTOs.Request.CreateAddressRequest;
import com.example.SpringBoot.DTOs.Response.AddressResponse;
import com.example.SpringBoot.Model.Address;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Repository.AddressRepository;
import com.example.SpringBoot.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper mapper;
    private final UserService userService;

    public AddressService(AddressRepository addressRepository, AddressMapper mapper, UserService userService) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public AddressResponse addAddress(CreateAddressRequest request){
        User user = userService.getUserEntityById(request.getUserId());
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setUser(user);

        addressRepository.save(address);
        return mapper.toResponse(address);
    }

    public List<AddressResponse> getAddressByUser(Integer userId){
        return addressRepository.findByUserId(userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
