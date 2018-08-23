package com.duru.socialpaper.exception;

public class UnAuthorization extends RuntimeException {

    public UnAuthorization() {
    }

    public UnAuthorization(String message) {
        super(message);
    }
}
