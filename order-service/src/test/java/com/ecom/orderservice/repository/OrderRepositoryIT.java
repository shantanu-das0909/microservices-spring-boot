package com.ecom.orderservice.repository;

import com.ecom.orderservice.config.TestContainerConfiguration;
import com.ecom.orderservice.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@Import(TestContainerConfiguration.class)
class OrderRepositoryIT{

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Should save a Order and retrieve it via custom skuCode")
    void shouldPersistAndFindProductBySkuCode() {
        Order newOrder = Order.builder()
                .skuCode("IPHONE_17")
                .price(BigDecimal.valueOf(90000))
                .quantity(1).build();

        orderRepository.save(newOrder);
        Optional<Order> retrievedOrder = orderRepository.findBySkuCode("IPHONE_17");

        assertThat(retrievedOrder).isPresent();
        assertThat(retrievedOrder.get().getId()).isNotNull();
        assertThat(retrievedOrder.get().getSkuCode()).isEqualTo("IPHONE_17");
        assertThat(retrievedOrder.get().getPrice()).isEqualTo(BigDecimal.valueOf(90000));
        assertThat(retrievedOrder.get().getQuantity()).isEqualTo(1);
    }
}
