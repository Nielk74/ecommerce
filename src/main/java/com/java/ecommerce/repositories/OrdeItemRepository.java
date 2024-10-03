package com.java.ecommerce.repositories;

import com.java.ecommerce.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdeItemRepository extends JpaRepository<OrderItem, Integer> {

}
