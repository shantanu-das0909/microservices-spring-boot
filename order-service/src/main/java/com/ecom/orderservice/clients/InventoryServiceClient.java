package com.ecom.orderservice.clients;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/inventory")
public interface InventoryServiceClient {

    @GetExchange
    public boolean checkInventory(@RequestParam String skuCode, @RequestParam Integer quantity);
}
