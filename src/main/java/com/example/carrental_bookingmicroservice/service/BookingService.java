package com.example.carrental_bookingmicroservice.service;

import com.example.carrental_bookingmicroservice.dto.BookingRequest;
import com.example.carrental_bookingmicroservice.model.Booking;
import com.example.carrental_bookingmicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking bookCar(BookingRequest bookingRequest) { //Todo remake the whole method
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = (LocalDate) dtf.parse(bookingRequest.getStartDate());
        LocalDate endDate = (LocalDate) dtf.parse(bookingRequest.getEndDate());
        long days = ChronoUnit.DAYS.between(startDate, endDate); //Todo mb add + 1
        //double totalCost = car.getPricePerDayUsd() * days; //Todo think about getting pricePerCar somehow

        return bookingRepository.save(new Booking(
                bookingRequest.getUserId(),
                bookingRequest.getCarId(),
                bookingRequest.getStartDate(),
                bookingRequest.getEndDate()
        ));
    }

    public Optional<Booking> findById(String id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<List<Booking>> findByUserId(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Optional<List<Booking>> findByUserIdAndActive(String userId, boolean active) {
        return bookingRepository.findByUserIdAndActive(userId, active);
    }

    public void deleteById(String id) {
        bookingRepository.deleteById(id);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }
}
