package com.example.carrental_bookingmicroservice.service;

import com.example.carrental_bookingmicroservice.model.ValidCar;
import com.example.carrental_bookingmicroservice.repository.ValidCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidCarService {
    @Autowired
    private ValidCarRepository validCarRepository;


    public ValidCar save(ValidCar validCar) {
        return validCarRepository.save(validCar);
    }
}
