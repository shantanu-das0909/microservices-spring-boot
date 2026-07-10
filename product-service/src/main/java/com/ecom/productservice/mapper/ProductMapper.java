package com.ecom.productservice.mapper;

import com.ecom.productservice.dto.AddProductRequest;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toEntity(AddProductRequest addProductRequest);

    ProductResponse toDTO(Product product);

    List<ProductResponse> toProductResposneList(List<Product> products);
}
