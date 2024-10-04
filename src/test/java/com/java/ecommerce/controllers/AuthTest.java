package com.java.ecommerce.controllers;

import com.java.ecommerce.dto.user.SignInDto;
import com.java.ecommerce.dto.user.SignUpDto;
import com.java.ecommerce.enums.Role;
import com.java.ecommerce.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Activate the 'test' profile
public class AuthTest {
    @Autowired
    private AuthController authController;


    @Autowired
    private UserService userService;


    @BeforeEach
    public void setUp() {

    }
    @Test
    public void signInNotAllowed(){
        assertThrows(AuthenticationException.class, () ->
            authController.signIn(new SignInDto("aaa","vvvv"))
        );
    }

    @Test
    public void signInAllowed(){
        assertEquals(authController.signUp(new SignUpDto("username","password", Role.ADMIN)).getStatusCode(), HttpStatus.CREATED);
        assertEquals(authController.signIn(new SignInDto("username", "password")).getStatusCode(),HttpStatus.OK);
    }


}
