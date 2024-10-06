package com.java.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.config.auth.JwtService;
import com.java.ecommerce.dto.user.LoginResponseDto;
import com.java.ecommerce.dto.user.SignInDto;
import com.java.ecommerce.dto.user.SignUpDto;
import com.java.ecommerce.models.User;
import com.java.ecommerce.services.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
 private final JwtService jwtService;
    
    private final AuthService authenticationService;

    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody SignUpDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody SignInDto loginUserDto) {
        User authenticatedUser = authenticationService.signin(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);

    }}