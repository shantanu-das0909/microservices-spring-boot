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

    public String addProduct(AddInventoryRequest addInventoryRequest) {
        Inventory inventory = inventoryMapper.toEntity(addInventoryRequest);

        Inventory savedProduct = inventoryRepository.save(inventory);
        return "Product saved to inventory with id: " + savedProduct.getId();
    }

    public String updateProduct(AddInventoryRequest addInventoryRequest) {
        Inventory inventory = inventoryMapper.toEntity(addInventoryRequest);
        Inventory updatedProduct = inventoryRepository.save(inventory);
        return "Product updated to inventory with id: " + updatedProduct.getId();
    }
}
