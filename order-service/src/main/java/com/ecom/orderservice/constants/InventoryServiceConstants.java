package com.ecom.orderservice.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "services.inventory-service")
public record InventoryServiceConstants(
        String baseUrl,
        Duration connectionTimeout,
        Duration readTimeout
) {}
