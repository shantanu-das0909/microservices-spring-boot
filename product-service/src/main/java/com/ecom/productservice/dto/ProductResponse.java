package com.ecom.productservice.dto;

import java.math.BigDecimal;

public record AddProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price
) {

}
