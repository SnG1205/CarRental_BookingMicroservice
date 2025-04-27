package com.example.carrental_bookingmicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "valid_cars")
public class ValidCar {
    @Id
    private String id;

    public ValidCar() {}

    public ValidCar(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
