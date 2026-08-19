package com.example.SpringBoot.Service;

import com.example.SpringBoot.DTOs.Request.CreateAddressRequest;
import com.example.SpringBoot.DTOs.Request.UpdateAddressDTO;
import com.example.SpringBoot.DTOs.Response.AddressResponse;
import com.example.SpringBoot.Model.Address;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Repository.AddressRepository;
import com.example.SpringBoot.mapper.AddressMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

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

    @Transactional
    public void updateAddress(Integer id , String newStreet,
                              String newCity, String newCountry){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        address.setStreet(newStreet);
        address.setCity(newCity);

        addressRepository.save(address);

        if(newCountry.isEmpty() ||newCountry.isBlank()){
            throw  new IllegalArgumentException("Country cannot be empty");
        }

        address.setCountry(newCountry);
        addressRepository.save(address);
    }
}
