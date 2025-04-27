package com.example.carrental_bookingmicroservice.dto;

import java.time.LocalDate;

public class BookingResponse {
    private String bookingId;
    private String userName; //Todo mb change/delete since Booking table is different now
    private String carModel; //Todo mb change/delete since Booking table is different now
    private String carBrand; //Todo mb change/delete since Booking table is different now
    private String licensePlate; //Todo mb change/delete since Booking table is different now
    private String startDate;
    private String endDate;
    private double totalCostUsd;

    public BookingResponse() {
    }

    public BookingResponse(String bookingId, String carBrand, String carModel, String endDate, String licensePlate, String startDate, double totalCostUsd, String userName) {
        this.bookingId = bookingId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.endDate = endDate;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.totalCostUsd = totalCostUsd;
        this.userName = userName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getTotalCostUsd() {
        return totalCostUsd;
    }

    public void setTotalCostUsd(double totalCostUsd) {
        this.totalCostUsd = totalCostUsd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
