package com.example.SpringBoot.Controller;

import com.example.SpringBoot.DTOs.Request.CreateAddressRequest;
import com.example.SpringBoot.DTOs.Response.AddressResponse;
import com.example.SpringBoot.Service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@RequestBody CreateAddressRequest request){
        AddressResponse response = service.addAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponse>> getByUser(@PathVariable Integer userId){
        return ResponseEntity.ok(service.getAddressByUser(userId));
    }

}
