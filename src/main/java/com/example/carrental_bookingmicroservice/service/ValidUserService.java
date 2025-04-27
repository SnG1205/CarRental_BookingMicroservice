package com.example.carrental_bookingmicroservice.service;

import com.example.carrental_bookingmicroservice.model.ValidUser;
import com.example.carrental_bookingmicroservice.repository.ValidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidUserService {

    @Autowired
    private ValidUserRepository validUserRepository;

    public ValidUser save(ValidUser validUser) {
        return validUserRepository.save(validUser);
    }
}
