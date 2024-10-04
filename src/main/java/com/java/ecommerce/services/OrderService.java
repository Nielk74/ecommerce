package com.java.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.repositories.OrderRepository;
import com.java.ecommerce.repositories.UserRepository;
import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.models.Order;
import com.java.ecommerce.models.User;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<OrderDto> getOrderByUserId(Integer userId) {
        Optional<User> optionUser = userService.getUserById(userId);

        if (optionUser.isPresent()) {
            return optionUser.get().getOrders().stream()
                    .map(OrderDto::new)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }
}
