package com.duru.socialpaper.exception;

public class InvalidException extends  RuntimeException{

    public InvalidException() {
    }

    public InvalidException(String message) {
        super(message);
    }
}
