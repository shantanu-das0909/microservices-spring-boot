package com.ecom.orderservice.services;

import com.ecom.orderservice.clients.InventoryServiceClient;
import com.ecom.orderservice.dto.AddProductRequest;
import com.ecom.orderservice.dto.OrderRequest;
import com.ecom.orderservice.mapper.OrderMapper;
import com.ecom.orderservice.model.Order;
import com.ecom.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryServiceClient inventoryServiceClient;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

//    public String placeOrder(String productId) {
//        String response = inventoryServiceClient.checkInventory(productId);
//        return response != null && response.equals("IN STOCK")
//                ? "Order placed successfully" : "Order can not be placed as product is out of stock";
//    }

    public String placeOrder(OrderRequest orderRequest) {

        Order order = orderMapper.toEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);

        return "Order is saved with id" + savedOrder.getId();
    }

}
