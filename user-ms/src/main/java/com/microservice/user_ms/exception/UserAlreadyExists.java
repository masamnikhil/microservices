package com.microservice.user_ms.exception;

public class UserAlreadyExists extends RuntimeException{
    UserAlreadyExists(String msg){
        super(msg);
    }
}
