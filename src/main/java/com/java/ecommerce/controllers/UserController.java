package com.java.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.dto.OrderDto;
import com.java.ecommerce.dto.UserDto;
import com.java.ecommerce.models.User;
import com.java.ecommerce.services.OrderService;
import com.java.ecommerce.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> usersDto = userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(usersDto,HttpStatus.OK);

    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
        Optional<User> optionalUser= userService.getUserById(userId);

        if(optionalUser.isPresent()) {
        UserDto userDto = new UserDto(userService.getUserById(userId).get());
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    
    
    // need to have header including user token
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderDto>> getAllOrdersByUserId(@PathVariable("userId") Integer userId){
        List<OrderDto> ordersDto = orderService.getOrderByUserId(userId);
         
        return new ResponseEntity<List<OrderDto>>(ordersDto,HttpStatus.OK);
    }
}