package com.jdvpl.backend.repositories.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "people")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private  String name;
    @NotBlank
    private  String lastName;
    @Column(name="email",unique = true)
    @NotBlank
    @Email(message = "El email no es válido")
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
