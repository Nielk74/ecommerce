package com.java.ecommerce.controllers;

import java.util.List;

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

import com.java.ecommerce.common.ApiResponse;
import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.models.Order;
import com.java.ecommerce.models.OrderItem;
import com.java.ecommerce.models.User;
import com.java.ecommerce.services.OrderService;
import com.java.ecommerce.services.ProductService;
import com.java.ecommerce.services.UserService;

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

        List<OrderItem> orderItems = orderService.getOrderItems(orderDto);

        Order newOrder = new Order(orderItems, user)
                .calculateAndSetTotalPrice();

        newOrder = orderService.addOrder(newOrder);

        return new ResponseEntity<>(new ApiResponse(true, "Order has been created with id " + newOrder.getId()),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Remove an order", description = "")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> removeProduct(@PathVariable("orderId") Integer orderId) {
        Order productToDelete = orderService.getOrderById(orderId);
        orderService.removeOrder(productToDelete);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order has been removed"),
                HttpStatus.OK);
    }
}
