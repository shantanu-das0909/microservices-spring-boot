package com.ecom.orderservice.mapper;

import com.ecom.orderservice.dto.OrderRequest;
import com.ecom.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order toEntity(OrderRequest orderRequest);

    OrderRequest toDTO(Order order);
}
