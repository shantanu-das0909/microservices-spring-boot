package com.ecom.productservice.controller;

import com.ecom.productservice.dto.AddProductRequest;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse addProduct(@RequestBody AddProductRequest addProductRequest) {
        return productService.createProduct(addProductRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
