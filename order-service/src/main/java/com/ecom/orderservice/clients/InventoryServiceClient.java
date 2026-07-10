package com.ecom.orderservice.clients;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryServiceClient {

    private final RestClient restClient;

    public InventoryServiceClient(@Qualifier("inventoryRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public String checkInventory(String productId) {
        return restClient.get()
                .uri("/inventory/{productId}", productId)
                .retrieve()
                .body(String.class);
    }

}
