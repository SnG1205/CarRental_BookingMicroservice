package com.example.carrental_bookingmicroservice.controller;

import com.example.carrental_bookingmicroservice.dto.BookingCloseRequestedMessage;
import com.example.carrental_bookingmicroservice.dto.BookingRequest;
import com.example.carrental_bookingmicroservice.dto.BookingRequestedMessage;
import com.example.carrental_bookingmicroservice.dto.BookingResponse;
import com.example.carrental_bookingmicroservice.kafka.producer.KafkaProducerService;
import com.example.carrental_bookingmicroservice.model.Booking;
import com.example.carrental_bookingmicroservice.service.BookingService;
import com.example.carrental_bookingmicroservice.utils.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    private JsonConverter jsonConverter = new JsonConverter();

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping //Todo mb change return type to smthing else since method works differently now
    public String bookCar(@RequestBody BookingRequest request) throws JsonProcessingException {
        BookingRequestedMessage bookingRequestedMessage = new BookingRequestedMessage(
                request.getUserId(),
                request.getCarId(),
                request.getStartDate(),
                request.getEndDate()
        );
        String message = jsonConverter.toJson(bookingRequestedMessage);
        kafkaProducerService.sendBookingRequestedMessage("booking-requested-topic", message);
        return "Success"; //Todo implementation
    }

    @PatchMapping("/{bookingId}/return")
    public Booking returnCar(@PathVariable String bookingId) throws JsonProcessingException {
        Optional<Booking> bookingOpt = bookingService.findById(bookingId);
        BookingCloseRequestedMessage bookingCloseRequestedMessage = new BookingCloseRequestedMessage(bookingOpt.get().getId(),bookingOpt.get().getCarId());
        kafkaProducerService.sendBookingRequestedMessage("booking-close-topic", jsonConverter.toJson(bookingCloseRequestedMessage));


        if (bookingOpt.isEmpty()) {
            //return "Booking not found.";
        }

        Booking booking = bookingOpt.get();
        //Car car = booking.getCar();

        //if (car.isAvailable()) {
        //return "Car is already returned.";
        //}

        //car.setAvailable(true);
        booking.setActive(false);
        //carService.save(car); //Todo kafka publish message to update the available field in Car Service
        bookingService.save(booking);

        return null;
    }

    @GetMapping("/bookings/{userId}")
    public Optional<List<Booking>> getBookingsForUser(@PathVariable String userId) {
        return bookingService.findByUserIdAndActive(userId, true);
    }

    @GetMapping("/history/{userId}")
    public Optional<List<Booking>> getBookingsHistoryForUser(@PathVariable String userId) {
        return bookingService.findByUserIdAndActive(userId, false);
    }
}
