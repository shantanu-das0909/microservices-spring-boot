package com.ecom.inventoryservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddInventoryRequest {
    private String skuCode;
    private Integer quantity;
}
