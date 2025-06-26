package com.example.inventoryservice.repository;

import com.example.inventoryservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository
        extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    List<Product> findByCategoryAndQuantityGreaterThan(String category, int minQuantity);

    List<Product> findByCategoryIgnoreCase(String category);

}