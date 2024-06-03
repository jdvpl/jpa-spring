package com.jdvpl.backend.controller.dto;

import com.jdvpl.backend.utils.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    @NotBlank
    private  String name;
    @NotBlank
    private  String lastName;
    @NotBlank
    @Email(message = "Invalid Email")
    private  String username;
    @NotBlank
    private  String pass;
    @Enumerated(EnumType.STRING)
    private Role role;


}
