package com.microservice.user_ms.exception;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(String msg){
        super(msg);
    }
}
