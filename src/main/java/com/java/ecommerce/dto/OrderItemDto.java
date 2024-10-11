package com.java.ecommerce.dto;

import com.java.ecommerce.models.OrderItem;

public record OrderItemDto(
        Integer productId,
        int quantity,
        double price) {
    public OrderItemDto(OrderItem orderItem) {
        this(orderItem.getProduct().getId(), orderItem.getQuantity(), orderItem.getPrice());
    }
}