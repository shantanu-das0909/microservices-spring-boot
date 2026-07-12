package com.ecom.productservice.repository;

import com.ecom.productservice.config.TestContainerConfiguration;
import com.ecom.productservice.model.Product;
import lombok.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

@DataMongoTest
@ActiveProfiles("test")
@Import(TestContainerConfiguration.class)
class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should save a product and retrieve it accurately from MongoDB collection")
    void shouldPersistAndFindDocument() {
        Product newProduct = Product.builder()
                .name("Headphones")
                .description("They are Noise Cancelling Headphones")
                .price(BigDecimal.valueOf(2000))
                .build();

        Product savedProduct = productRepository.save(newProduct);

        Optional<@NonNull Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertThat(foundProduct).isPresent();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("Headphones");
        assertThat(savedProduct.getPrice()).isEqualTo(BigDecimal.valueOf(2000));
        assertThat(savedProduct.getDescription()).isEqualTo("They are Noise Cancelling Headphones");

    }
}
