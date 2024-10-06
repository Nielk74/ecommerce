package com.java.ecommerce.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.ecommerce.dto.user.SignInDto;
import com.java.ecommerce.dto.user.SignUpDto;
import com.java.ecommerce.enums.Role;
import com.java.ecommerce.exceptions.InvalidJwtException;
import com.java.ecommerce.exceptions.UserAlreadyExistsException;
import com.java.ecommerce.models.User;
import com.java.ecommerce.repositories.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean hasAccess(User user, Integer userId) {
        return user.getId().equals(userId) || isAdmin(user);
    }

    private boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }

    public User signin(SignInDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.login(),
                        input.password()));

        return userRepository.findByLogin(input.login()).orElseThrow();
    }

    public User signup(SignUpDto input) {
        if (userRepository.findByLogin(input.login()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setLogin(input.login());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRole(input.role());

        return userRepository.save(user);
    }
}
