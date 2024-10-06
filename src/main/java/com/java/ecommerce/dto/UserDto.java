package com.java.ecommerce.dto;

import com.java.ecommerce.enums.Role;
import com.java.ecommerce.models.User;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private @NotNull String login;
    private @NotNull Role role;
    public UserDto(User user){
        this.login = user.getLogin();
        this.role = user.getRole();
    }

}
