package com.myhomemadeshop.api.infrastructure.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
