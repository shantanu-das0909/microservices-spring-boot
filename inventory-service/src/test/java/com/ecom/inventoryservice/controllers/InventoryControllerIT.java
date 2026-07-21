package com.ecom.inventoryservice.controllers;

import com.ecom.inventoryservice.config.TestContainerConfiguration;
import com.ecom.inventoryservice.models.Inventory;
import com.ecom.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@Import(TestContainerConfiguration.class)
public class InventoryControllerIT {

    @Autowired
    RestTestClient restTestClient;
    
    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    void shouldCheckInventoryForInStockProduct() {

        Inventory newInventoryRecord = Inventory.builder().skuCode("IPHONE_17").quantity(50).build();
        inventoryRepository.save(newInventoryRecord);

        restTestClient.get()
                .uri("/api/inventory?skuCode=IPHONE_17&quantity=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class).isEqualTo(true);
    }

    @Test
    void shouldCheckInventoryForOutOfStockProductForSkuCodeNotFound() {

        Inventory newInventoryRecord = Inventory.builder().skuCode("HEADPHONE").quantity(2).build();
        Inventory savedInventory = inventoryRepository.save(newInventoryRecord);

        restTestClient.get()
                .uri("/api/inventory?skuCode=MACBOOK_AIR_M5&quantity=1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class).isEqualTo(false);
    }

    @Test
    void shouldCheckInventoryForOutOfStockProductForNotEnoughQuantity() {

        Inventory newInventoryRecord = Inventory.builder().skuCode("HEADPHONE").quantity(2).build();
        Inventory savedInventory = inventoryRepository.save(newInventoryRecord);

        restTestClient.get()
                .uri("/api/inventory?skuCode=HEADPHONE&quantity=3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class).isEqualTo(false);
    }
}
