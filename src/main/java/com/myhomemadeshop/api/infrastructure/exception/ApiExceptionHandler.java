package com.myhomemadeshop.api.infrastructure.exception;

import com.myhomemadeshop.api.infrastructure.exception.pojo.ApiError;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ApiError apiError = new ApiError(ex.getBindingResult(), HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleException(EntityNotFoundException ex) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND));
  }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleException(ConflictException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(ex.getMessage(), HttpStatus.CONFLICT));
    }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST));
  }


}
