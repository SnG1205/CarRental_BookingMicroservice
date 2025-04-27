package com.example.carrental_bookingmicroservice.controller;

import com.example.carrental_bookingmicroservice.dto.BookingRequest;
import com.example.carrental_bookingmicroservice.dto.BookingResponse;
import com.example.carrental_bookingmicroservice.model.Booking;
import com.example.carrental_bookingmicroservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping //Todo mb change return type to smthing else since method works differently now
    public BookingResponse bookCar(@RequestBody BookingRequest request){
        return null; //Todo implementation
    }

    @PatchMapping("/{bookingId}/return")
    public Booking returnCar(@PathVariable String bookingId) {
        Optional<Booking> bookingOpt = bookingService.findById(bookingId);

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

        return booking;
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
