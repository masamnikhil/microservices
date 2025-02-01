package com.microservice.user_ms;

public enum Role {
    USER,
    ADMIN;

    public String getRole(){
        return this.name();
    }
}
