package com.example.SpringBoot.Service;

import com.example.SpringBoot.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean CountUser(){
        try{
            userRepository.count();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
