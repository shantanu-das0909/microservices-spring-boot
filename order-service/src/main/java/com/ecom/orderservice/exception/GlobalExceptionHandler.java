package com.ecom.orderservice.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    public ResponseEntity<@NonNull String> handleRateLimitVendorException(HttpClientErrorException.TooManyRequests ex) {
        log.error("Rate limit exception: ", ex);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage());
    }

    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<@NonNull String> handleRateLimitException(RateLimitException ex) {
        log.error("Rate limit exception: ", ex);
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<@NonNull String> handleServiceUnavailableException(ServiceUnavailableException ex) {
        log.error("Service unavailable exception: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<@NonNull String> handleGenericFallback(Exception ex) {
        log.error("Unhandled exception caught by global fallback: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
