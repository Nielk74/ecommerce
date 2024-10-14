package com.java.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.dto.OrderItemDto;
import com.java.ecommerce.exceptions.InvalidOrderException;
import com.java.ecommerce.models.Order;
import com.java.ecommerce.models.OrderItem;
import com.java.ecommerce.models.Product;
import com.java.ecommerce.models.User;
import com.java.ecommerce.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

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

    public void removeOrder(Order order) {
        this.orderRepository.delete(order);
    }

    // parse list of order item
    public List<OrderItem> getOrderItems(OrderDto orderDto) {
        return orderDto.orderItems()
                .stream()
                .map(this::getOrderItem)
                .collect(Collectors.toList());

    }

    // parse ONE order item
    public OrderItem getOrderItem(OrderItemDto orderItemDto) {
        Product product = productService.getProductById(orderItemDto.product().getId());
        return new OrderItem(orderItemDto, product);
    }

}
