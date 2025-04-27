package com.example.carrental_bookingmicroservice.repository;

import com.example.carrental_bookingmicroservice.model.ValidUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidUserRepository extends MongoRepository<ValidUser, String> {
}
