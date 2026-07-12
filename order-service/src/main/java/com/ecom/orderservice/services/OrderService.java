package com.ecom.orderservice.services;

import com.ecom.orderservice.clients.InventoryServiceClient;
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

    public String placeOrder(OrderRequest orderRequest) {

        try {
            boolean isInStock = inventoryServiceClient.checkInventory(orderRequest.skuCode(), orderRequest.quantity());
            if(!isInStock) {
                return "Order can't be placed, as the product with skuCode: "
                        + orderRequest.skuCode() + " is Out of Stock!";
            }
        } catch(Exception e){
            log.error(e.getMessage());
        }

        Order order = orderMapper.toEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);

        return "Order is placed with id" + savedOrder.getId();
    }

}
