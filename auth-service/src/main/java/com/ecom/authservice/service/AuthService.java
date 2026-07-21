package com.ecom.authservice.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String getToken() {
        return "token";
    }
}
