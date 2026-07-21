package com.ecom.authservice.dto;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {
}
