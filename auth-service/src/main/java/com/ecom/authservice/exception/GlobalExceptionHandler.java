package com.ecom.authservice.exception;

import com.ecom.authservice.dto.AuthExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialException(Exception ex, WebRequest request) {
        AuthExceptionResponse badCredentials = AuthExceptionResponse.builder()
                .error("Authentication failed")
                .message(ex.getMessage())
                .status(String.valueOf(HttpStatus.UNAUTHORIZED)).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(badCredentials);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> handleDisabledException(Exception ex, WebRequest request) {
        AuthExceptionResponse accountDisabled = AuthExceptionResponse.builder()
                .error("Authentication failed")
                .message(ex.getMessage())
                .status(String.valueOf(HttpStatus.FORBIDDEN)).build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accountDisabled);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(Exception ex, WebRequest request) {
        AuthExceptionResponse usernameNotFound = AuthExceptionResponse.builder()
                .error("Authentication failed")
                .message(ex.getMessage())
                .status(String.valueOf(HttpStatus.UNAUTHORIZED)).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usernameNotFound);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthException(Exception ex, WebRequest request) {
        AuthExceptionResponse authenticationFailed = AuthExceptionResponse.builder()
                .error("Authentication failed")
                .message(ex.getMessage())
                .status(String.valueOf(HttpStatus.UNAUTHORIZED)).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationFailed);
    }
}
