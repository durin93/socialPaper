package com.duru.socialpaper.exception;

public class NoAuthentication extends RuntimeException{

    public NoAuthentication() {
    }

    public NoAuthentication(String message) {
        super(message);
    }
}
