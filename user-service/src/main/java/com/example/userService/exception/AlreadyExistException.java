package com.example.userService.exception;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String msg){
        super(msg);
    }
}
