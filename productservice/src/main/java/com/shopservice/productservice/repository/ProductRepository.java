package com.shopservice.productservice.repository;

import com.shopservice.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    ProductRepository extends JpaRepository<Product,Long> {
}
