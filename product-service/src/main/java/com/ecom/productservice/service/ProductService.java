package com.ecom.productservice.service;

import com.ecom.productservice.dto.AddProductRequest;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.mapper.ProductMapper;
import com.ecom.productservice.model.Product;
import com.ecom.productservice.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(AddProductRequest addProductRequest) {
        Product product = productMapper.toEntity(addProductRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return productMapper.toProductResposneList(allProducts);
    }

}
