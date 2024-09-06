package com.java.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ecommerce.models.AuthenticationToken;
import com.java.ecommerce.models.User;
@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
