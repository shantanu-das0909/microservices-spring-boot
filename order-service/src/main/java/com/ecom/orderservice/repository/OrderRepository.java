package com.ecom.orderservice.repository;

import com.ecom.orderservice.model.Order;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<@NonNull Order, @NonNull UUID> {
}
