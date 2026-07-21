package com.ecom.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
public record OrderRequest(
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
