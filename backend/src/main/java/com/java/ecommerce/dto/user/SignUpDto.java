package com.java.ecommerce.dto.user;

import com.java.ecommerce.enums.Role;

public record SignUpDto(
        String login,
        String password,
        Role role) {
}