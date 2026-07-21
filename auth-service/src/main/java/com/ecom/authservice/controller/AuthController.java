package com.ecom.authservice.controller;

import com.ecom.authservice.dto.AuthRequest;
import com.ecom.authservice.dto.AuthResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<@NonNull AuthResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        // TODO: generate the token

        AuthResponse token = AuthResponse.builder().token("jwt token").build();
        return ResponseEntity.ok(token);
    }

    @GetMapping("/health")
    public String getHealth() {
        return "Healthy";
    }
}
