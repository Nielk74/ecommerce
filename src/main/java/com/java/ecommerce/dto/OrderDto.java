package com.java.ecommerce.dto;

import java.util.List;

import com.java.ecommerce.models.Order;
import java.util.stream.Collectors;

public record OrderDto(
        Integer id,
        double total_price,
        List<OrderItemDto> orderItems) {

    public OrderDto(Order order) {
        this(order.getId(), order.getTotal_price(), order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList()));
    }
}