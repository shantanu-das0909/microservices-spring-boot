package com.ecom.orderservice.services;

import com.ecom.orderservice.clients.InventoryServiceClient;
import com.ecom.orderservice.exception.RateLimitException;
import com.ecom.orderservice.exception.ServiceUnavailableException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryServiceClient inventoryServiceClient;

    @Retry(name="inventoryRetry", fallbackMethod = "checkInventoryFallback")
//    @RateLimiter(name = "outboundLimiter", fallbackMethod = "chackInventoryRateLimiterFallBack")
    public boolean checkInventory(String skuCode, Integer quantity) {
        log.info("Calling inventory service for SKU: {}", skuCode);
        return inventoryServiceClient.checkInventory(skuCode, quantity);
    }

    public boolean checkInventoryFallback(String skuCode, Integer quantity, Throwable exception) {
        if (exception instanceof HttpClientErrorException.TooManyRequests || exception.getCause() instanceof HttpClientErrorException.TooManyRequests) {
            throw new RateLimitException(exception.getMessage());
        }
        log.error("Inventory check failed after all retries for SKU: {}. Error: {}", skuCode, exception.getMessage());
        throw new ServiceUnavailableException("Inventory service is unavailable. Please try again shortly.");
    }

//    public boolean chackInventoryRateLimiterFallBack(String skuCode, Integer quantity, RequestNotPermitted ex) {
//        log.warn("Rate limit exceeded for SKU: {} with quantity: {}. Reason: {}", skuCode, quantity, ex.getMessage());
//        throw new RateLimitException("Inventory service is currently busy handling too many requests. Please try again shortly.");
//    }
}
