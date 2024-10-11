package com.java.ecommerce.dto;

import com.java.ecommerce.enums.Role;
import com.java.ecommerce.models.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private @NotNull String login;
    private @NotNull Role role;
    private Integer id;
    public UserDto(User user){
        this.login = user.getLogin();
        this.role = user.getRole();
        this.id = user.getId();
    }

}
