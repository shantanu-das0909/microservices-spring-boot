package com.ecom.inventoryservice.services;

import com.ecom.inventoryservice.exception.RateLimitException;
import com.ecom.inventoryservice.mapper.InventoryMapper;
import com.ecom.inventoryservice.repository.InventoryRepository;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @RateLimiter(name = "inboundLimiter", fallbackMethod = "chackInventoryRateLimiterFallBack")
    public boolean isInStock(String skuCode, Integer quantity) throws Exception {
        log.debug("Checking is the product with SKU_ID: {} is in stock", skuCode);
//        if(skuCode != null) {
//            throw new Exception("Retry check");
//        }
//        return false;
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

    public boolean chackInventoryRateLimiterFallBack(String skuCode, Integer quantity, RequestNotPermitted ex) {
        log.warn("Rate limit exceeded for SKU: {} with quantity: {}. Reason: {}", skuCode, quantity, ex.getMessage());
        throw new RateLimitException("Inventory service is currently busy handling too many requests. Please try again shortly.");
    }
}
