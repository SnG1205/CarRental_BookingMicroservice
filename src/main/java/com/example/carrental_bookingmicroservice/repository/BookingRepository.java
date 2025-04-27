package com.example.carrental_bookingmicroservice.repository;

import com.example.carrental_bookingmicroservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Optional<List<Booking>> findByUserId(String userId);
    Optional<List<Booking>> findByUserIdAndActive(String userId, Boolean active);
}
