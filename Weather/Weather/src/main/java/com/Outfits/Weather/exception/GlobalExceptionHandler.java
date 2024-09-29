package com.Outfits.Weather.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<String> handleRateLimitException(RateLimitException ex) {
        String responseMessage = "Rate limit exceeded. Please try again later.";
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(responseMessage);
    }
}

