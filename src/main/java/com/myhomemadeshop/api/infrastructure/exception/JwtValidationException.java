package com.myhomemadeshop.api.infrastructure.exception;

public class JwtValidationException extends RuntimeException {

  public JwtValidationException(String message) {
    super(message);
  }
}
