package com.ecom.inventoryservice.controllers;

import com.ecom.inventoryservice.dto.AddInventoryRequest;
import com.ecom.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public boolean checkInventory(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }

    @PostMapping
    public String addProductToInventory(@RequestBody AddInventoryRequest addInventoryRequest) {
        return inventoryService.addProduct(addInventoryRequest);
    }

    @PutMapping
    public String updateProductToInventory(@RequestBody AddInventoryRequest addInventoryRequest) {
        return inventoryService.updateProduct(addInventoryRequest);
    }
}
