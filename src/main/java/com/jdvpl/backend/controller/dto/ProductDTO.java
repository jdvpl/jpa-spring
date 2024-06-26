package com.jdvpl.backend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    @NotBlank

    private String name;

    private Float price;

    @NotBlank
    private String description;

    private int quantity;

    @NotBlank
    private String picture;

    @Builder.Default
    private Boolean status = true;

    @NotNull(message = "El id de la categoria es obligatorio")
    private Long categoryId;
}
