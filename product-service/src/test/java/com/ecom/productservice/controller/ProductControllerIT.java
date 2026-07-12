package com.ecom.productservice.controller;

import com.ecom.productservice.config.TestContainerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

@ActiveProfiles("test")
@AutoConfigureRestTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
class ProductControllerIT{

    @Autowired
    private RestTestClient restTestClient ;

    @Test
    void shouldCreateProduct(){
        String requestBody = """
                {
                    "name": "iPhone 13",
                    "description": "16gb RAM & 256gb storage",
                    "price": 50000
                }
                """;

        restTestClient.post()
                .uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.name").isEqualTo("iPhone 13")
                .jsonPath("$.description").isEqualTo("16gb RAM & 256gb storage")
                .jsonPath("$.price").isEqualTo(50000);

    }
}






















