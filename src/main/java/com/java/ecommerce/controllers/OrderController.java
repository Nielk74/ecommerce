package com.java.ecommerce.controllers;

import java.util.List;

import com.java.ecommerce.models.Order;
import com.java.ecommerce.services.UserService;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import com.java.ecommerce.common.ApiResponse;
import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.dto.OrderItemDto;
import com.java.ecommerce.dto.ProductDto;
import com.java.ecommerce.exceptions.InvalidProductException;
import com.java.ecommerce.models.Category;
import com.java.ecommerce.models.OrderItem;
import com.java.ecommerce.models.Product;
import com.java.ecommerce.models.User;
import com.java.ecommerce.services.OrderService;
import com.java.ecommerce.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Operation(summary = "Get all orders", description = "")
    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> body = orderService.getAllOrders();
        return new ResponseEntity<List<OrderDto>>(body, HttpStatus.OK);
    }

    @Operation(summary = "Get an order by id", description = "")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<OrderDto>(new OrderDto(order), HttpStatus.OK);
    }

    @Operation(summary = "Add an order", description = "")
    @PostMapping("/")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody OrderDto orderDto) {
        User user = userService.getUserById(orderDto.user().getId());
        
        Order newOrder = new Order(getOrderItems(orderDto), user).calculateAndSetTotalPrice();

        newOrder = orderService.addOrder(newOrder);

        return new ResponseEntity<>(new ApiResponse(true, "Order has been created with id " + newOrder.getId()), HttpStatus.CREATED);
    }
    private List<OrderItem> getOrderItems(OrderDto orderDto) {
        return orderDto.orderItems().stream().map(this::getOrderItem).collect(Collectors.toList());

    }


    private OrderItem getOrderItem(OrderItemDto orderItemDto) {
        Product product = productService.getProductById(orderItemDto.productId());
        return new OrderItem(orderItemDto, product);
    }

}
