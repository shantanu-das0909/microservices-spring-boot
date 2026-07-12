package com.ecom.inventoryservice.repository;

import com.ecom.inventoryservice.models.Inventory;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<@NonNull Inventory, @NonNull UUID> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

    Optional<Inventory> findBySkuCode(String skuCode);
}
