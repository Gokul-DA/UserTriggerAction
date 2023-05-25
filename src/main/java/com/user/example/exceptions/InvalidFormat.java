package com.user.example.exceptions;

public class InvalidFormat extends RuntimeException{

    String message;

    public InvalidFormat(String message){
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
