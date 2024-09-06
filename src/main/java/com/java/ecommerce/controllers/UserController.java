package com.java.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Registration.Signing;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.dto.ResponseDto;
import com.java.ecommerce.dto.SignupDto;
import com.java.ecommerce.dto.user.SignInDto;
import com.java.ecommerce.dto.user.SignInResponseDto;
import com.java.ecommerce.services.UserService;
import com.java.ecommerce.utils.Helper;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    // sign up
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }
}
