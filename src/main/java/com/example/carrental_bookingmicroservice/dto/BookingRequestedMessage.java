package com.example.carrental_bookingmicroservice.dto;

public class BookingRequestedMessage {
    private String userId;
    private String carId;
    private String startDate;
    private String endDate;

    public BookingRequestedMessage() {
    }

    public BookingRequestedMessage(String userId, String carId, String startDate, String endDate) {
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
