package com.example.SpringBoot.Service;

import com.example.SpringBoot.DTOs.Response.UserResponse;
import com.example.SpringBoot.Model.User;
import com.example.SpringBoot.Repository.QueryPracticeRepository;
import com.example.SpringBoot.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryPracticeService {

    private final QueryPracticeRepository repository ;
    private final UserMapper mapper;


    public QueryPracticeService(QueryPracticeRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserResponse> derived(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName)
                .stream().map(mapper::toResponse).toList();
    }

    public List<UserResponse> jpql(String firstName, String lastName) {
        return repository.searchByNameJPQL(firstName, lastName)
                .stream().map(mapper::toResponse).toList();
    }

    public List<UserResponse> nativeQuery(String firstName, String lastName) {
        return repository.searchByNameNative(firstName, lastName)
                .stream().map(mapper::toResponse).toList();
    }
}
