package com.example.carrental_bookingmicroservice.dto;

public class ValidUser {
    private String id;

    public ValidUser() {}

    public ValidUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
