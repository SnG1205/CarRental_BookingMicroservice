package com.example.carrental_bookingmicroservice.kafka.consumer;

import com.example.carrental_bookingmicroservice.dto.BookingCloseRequestedMessage;
import com.example.carrental_bookingmicroservice.dto.BookingCreatedMessage;
import com.example.carrental_bookingmicroservice.model.Booking;
import com.example.carrental_bookingmicroservice.model.ValidCar;
import com.example.carrental_bookingmicroservice.model.ValidUser;
import com.example.carrental_bookingmicroservice.service.BookingService;
import com.example.carrental_bookingmicroservice.service.ValidCarService;
import com.example.carrental_bookingmicroservice.service.ValidUserService;
import com.example.carrental_bookingmicroservice.utils.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Optional;

@Service
public class KafkaConsumerService {

    private JsonConverter jsonConverter = new JsonConverter();

    @Autowired
    private ValidUserService validUserService;
    @Autowired
    private ValidCarService validCarService;
    @Autowired
    private BookingService bookingService;

    @KafkaListener(topics = "user-created-topic1" , groupId = "my-group")
    public void consumeUserCreated(String message) throws JsonProcessingException {
        ValidUser validUser = jsonConverter.fromJson(message, ValidUser.class);
        validUserService.save(validUser);
        System.out.println("Received message: " + validUser.toString());
    }

    @KafkaListener(topics = "car-created-topic" , groupId = "my-group")
    public void consumeCarCreated(String message) throws JsonProcessingException {
        System.out.println("Received message: " + message);
        ValidCar validCar = jsonConverter.fromJson(message, ValidCar.class);
        validCarService.save(validCar);
        System.out.println("Received message: " + validCar.toString());
    }

    @KafkaListener(topics = "booking-created-topic" , groupId = "my-group")
    public void consumeBookingCreated(String message) throws JsonProcessingException {
        System.out.println("Received booking created message: " + message);
        BookingCreatedMessage bookingCreatedMessage = jsonConverter.fromJson(message, BookingCreatedMessage.class);
        bookingService.save(new Booking(
                bookingCreatedMessage.getUserId(),
                bookingCreatedMessage.getCarId(),
                bookingCreatedMessage.getStartDate(),
                bookingCreatedMessage.getEndDate(),
                bookingCreatedMessage.getTotalPriceUsd()
        ));
    }

    @KafkaListener(topics = "booking-ended-topic" , groupId = "my-group")
    public void consumeBookingEnded(String message) throws JsonProcessingException {
        System.out.println("Received booking ended message: " + message);
        BookingCloseRequestedMessage bookingCloseRequestedMessage = jsonConverter.fromJson(message, BookingCloseRequestedMessage.class);
        Optional<Booking> booking = bookingService.findById(bookingCloseRequestedMessage.getBookingId());
        if(booking.isPresent()) {
            Booking bookingToSave = booking.get();
            bookingToSave.setActive(false);
            bookingService.save(bookingToSave);
        }
    }
}
