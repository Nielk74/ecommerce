package com.java.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.exceptions.InvalidOrderException;
import com.java.ecommerce.models.Order;
import com.java.ecommerce.models.User;
import com.java.ecommerce.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByUserId(Integer userId) {
        User user = userService.getUserById(userId);

        return user
                .getOrders()
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());

    }

    public Order addOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public Order getOrderById(Integer id) {
        return this.orderRepository.findById(id).orElseThrow(() -> new InvalidOrderException());
    }
}
