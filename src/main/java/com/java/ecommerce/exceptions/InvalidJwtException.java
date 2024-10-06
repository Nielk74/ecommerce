package com.java.ecommerce.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtException extends AuthenticationException {
    public InvalidJwtException(String ex) {
        super(ex);
    }
}
