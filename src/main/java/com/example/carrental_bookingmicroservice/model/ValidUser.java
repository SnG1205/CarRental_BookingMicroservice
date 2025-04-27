package com.example.carrental_bookingmicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "valid_users")
public class ValidUser {
    @Id
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
