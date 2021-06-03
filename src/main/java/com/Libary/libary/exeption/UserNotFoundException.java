package com.Libary.libary.exeption;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
