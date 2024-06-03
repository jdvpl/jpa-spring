package com.jdvpl.backend.controller.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private  String email;
    @NotBlank
    private  String phone;
    @NotBlank
    private  String address;
    @NotBlank
    private  String city;
    @NotBlank
    private  String country;
    @NotNull
    private  Date birthDate;
}
