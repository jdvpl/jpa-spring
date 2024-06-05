package com.jdvpl.backend.controller.dto;

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
public class RegisterRequestDto {
    @NotBlank
    @Email(message = "Invalid Email")
    private  String username;
    @NotBlank
    private  String password;
    @NotBlank
    private  String name;
    @NotBlank
    private  String lastName;


}
