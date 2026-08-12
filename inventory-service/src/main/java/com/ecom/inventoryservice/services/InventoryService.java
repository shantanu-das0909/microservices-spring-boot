package com.ecom.inventoryservice.services;

import com.ecom.inventoryservice.mapper.InventoryMapper;
import com.ecom.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public boolean isInStock(String skuCode, Integer quantity) throws Exception {
        log.debug("Checking is the product with SKU_ID: {} is in stock", skuCode);
//        if(skuCode != null) {
//            throw new Exception("Retry check");
//        }
//        return false;
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }
}
