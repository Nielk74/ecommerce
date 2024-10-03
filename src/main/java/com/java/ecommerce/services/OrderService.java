package com.java.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.repositories.OrderRepository;
import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.models.Order;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
}
