package com.ecom.orderservice.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequest {

    private String name;
    private int quantity;
}
