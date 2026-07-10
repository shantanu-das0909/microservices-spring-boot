package com.ecom.productservice.dto;


import java.math.BigDecimal;

public record AddProductRequest (
        String name,
        String description,
        BigDecimal price
) {
}
