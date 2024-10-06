package com.java.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.java.ecommerce.dto.UserDto;
import com.java.ecommerce.models.User;
import com.java.ecommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByLogin(username).orElseThrow();
    }

    public List<UserDto> getAllUser(){
        return repository.findAll().stream()
            .map(UserDto::new)
            .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Integer id){
        return repository.findById(id);
    }
}