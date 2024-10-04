package com.java.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.common.ApiResponse;
import com.java.ecommerce.models.Order;
import com.java.ecommerce.services.OrderService;

import io.swagger.annotations.ApiOperation;

import com.java.ecommerce.dto.OrderDto;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "List all orders")
    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> body = orderService.getAllOrders();
        return new ResponseEntity<List<OrderDto>>(body, HttpStatus.OK);
    }

    // @ApiOperation(value = "List orders from a user", notes = "Provide username to list his orders")
    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable("userId") Integer userId) {
    //     List<OrderDto> body = orderService.
    //     return null;
    // }
    // @ApiOperation(value = "List orders from a user", notes = "Provide username to
    // list his orders")
    // @GetMapping("/username/{username}")
    // public ResponseEntity<ApiResponse> listOrders(@PathVariable("username")
    // String username) {
    // List<Order> orders = orderService.get(username);
    // return new ResponseEntity<ApiResponse>(new ApiResponse(orders),
    // org.springframework.http.HttpStatus.OK);
    // }

}
