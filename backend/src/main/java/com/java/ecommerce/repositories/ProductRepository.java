package com.java.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
 	Product findByName(String name);   
}
