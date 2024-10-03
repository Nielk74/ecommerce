package com.java.ecommerce.dto;

import com.java.ecommerce.models.OrderItem;

public record OrderItemDto(
        Integer id,
        ProductDto product,
        int quantity,
        double price) {
    public OrderItemDto(OrderItem orderItem) {
        this(orderItem.getId(), new ProductDto(orderItem.getProduct()), orderItem.getQuantity(), orderItem.getPrice());
    }
}