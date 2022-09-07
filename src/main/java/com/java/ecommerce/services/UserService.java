package com.java.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.dto.ResponseDto;
import com.java.ecommerce.dto.SignupDto;
import com.java.ecommerce.repositories.UserRepository;


@Service
public class UserService {

    @Autowired UserRepository userRepository;
    public ResponseDto signup(SignupDto signupDto) {

        //check if user is already present
        ResponseDto responseDto = new ResponseDto("success", "dummy");
        return responseDto;
    }
}
