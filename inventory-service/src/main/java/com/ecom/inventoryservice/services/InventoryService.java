package com.ecom.inventoryservice.services;

import com.ecom.inventoryservice.dto.AddInventoryRequest;
import com.ecom.inventoryservice.mapper.InventoryMapper;
import com.ecom.inventoryservice.models.Inventory;
import com.ecom.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
