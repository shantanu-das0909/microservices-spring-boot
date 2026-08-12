package com.ecom.orderservice.services;

import com.ecom.orderservice.clients.InventoryServiceClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryServiceClient inventoryServiceClient;

    @Retry(name="inventoryRetry", fallbackMethod = "checkInventoryFallback")
    public boolean checkInventory(String skuCode, Integer quantity) {
        log.info("Calling inventory service for SKU: {}", skuCode);
        return inventoryServiceClient.checkInventory(skuCode, quantity);
    }

    private boolean checkInventoryFallback(String skuCode, Integer quantity, Throwable exception) {
        log.error("Inventory check failed after all retries for SKU: {}. Error: {}", skuCode, exception.getMessage());
        return false;
    }
}
