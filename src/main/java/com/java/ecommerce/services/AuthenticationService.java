package com.java.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.models.AuthenticationToken;
import com.java.ecommerce.models.User;
import com.java.ecommerce.repositories.TokenRepository;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;


    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }
    
    public AuthenticationToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }
}
