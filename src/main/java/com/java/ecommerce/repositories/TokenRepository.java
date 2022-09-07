package com.java.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ecommerce.models.AuthenticationToken;
@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

}
