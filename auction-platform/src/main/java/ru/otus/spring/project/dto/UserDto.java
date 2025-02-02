package ru.otus.spring.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @Size(min = 5, max = 50, message = "Username must contain from 5 to 50 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 3, max = 255, message = "Password length must be from 3 to 255 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}