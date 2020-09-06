package com.myhomemadeshop.api.infrastructure.exception.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiError {

  private Map<String, String> messages;
  private String message;
  private LocalDateTime timestamp = LocalDateTime.now();
  private HttpStatus status;

  public ApiError(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public ApiError(BindingResult bindingResult, HttpStatus status) {
    this.status = status;
    messages = new HashMap<>();
    bindingResult.getFieldErrors().stream()
        .forEach(error -> messages.put(error.getField(), error.getDefaultMessage()));
  }
}
