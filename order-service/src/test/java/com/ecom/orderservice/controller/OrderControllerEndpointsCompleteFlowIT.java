package com.ecom.orderservice.controller;

import com.ecom.orderservice.config.TestContainerConfiguration;
import com.ecom.orderservice.dto.OrderRequest;
import com.ecom.orderservice.services.OrderService;
import com.ecom.orderservice.stub.InventoryClientStub;
import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
@Testcontainers
public class OrderControllerEndpointsCompleteFlowIT {

    @Container
    static WireMockContainer wireMock =
            new WireMockContainer("wiremock/wiremock:3.12.0");

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {

        registry.add(
                "services.inventory.base-url",
                wireMock::getBaseUrl
        );
    }

    @Autowired
    OrderService orderService;

    @BeforeEach
    void setup() {

        WireMock.configureFor(
                wireMock.getHost(),
                wireMock.getMappedPort(8080)
        );

        WireMock.reset();
    }


    @Test
    void shouldCreateOrderWhenInventoryIsAvailable() {
        //arrange
        InventoryClientStub.stubForInventoryCallCheckInventoryWhenInventoryIsAvailable();

        // act
        OrderRequest orderRequest = OrderRequest.builder()
                .skuCode("SAMSUNG_S25_ULTRA")
                .quantity(1)
                .price(BigDecimal.valueOf(100000))
                .build();

        String response = orderService.placeOrder(orderRequest);

        // assert
        assertNotNull(response);
        assertNotEquals("Order can't be placed, as the product with skuCode: SAMSUNG_S25_ULTRA is Out of Stock!", response);
        verify(getRequestedFor(urlEqualTo("/api/inventory?skuCode=SAMSUNG_S25_ULTRA&quantity=1")));

    }
}
