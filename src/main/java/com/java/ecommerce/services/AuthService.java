package com.java.ecommerce.services;

import org.springframework.stereotype.Service;

import com.java.ecommerce.enums.Role;
import com.java.ecommerce.models.User;

@Service
public class AuthService {
    public boolean hasAccess(User user, Integer userId) {
        return user.getId().equals(userId) || isAdmin(user);
    }

    private boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }
}
