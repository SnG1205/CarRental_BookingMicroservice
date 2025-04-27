package com.example.carrental_bookingmicroservice.repository;

import com.example.carrental_bookingmicroservice.model.ValidCar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidCarRepository extends MongoRepository<ValidCar, String> {
}
