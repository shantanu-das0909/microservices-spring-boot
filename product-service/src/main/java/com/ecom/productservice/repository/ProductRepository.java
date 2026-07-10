package com.ecom.productservice.repository;

import com.ecom.productservice.model.Product;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<@NonNull Product, @NonNull String> {
}
