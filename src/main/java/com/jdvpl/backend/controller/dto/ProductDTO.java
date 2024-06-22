package com.jdvpl.backend.controller.dto;

import java.math.BigDecimal;

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

    private String name;

    private BigDecimal price;

    private String description;

    private int quantity;

    private String picture;

    @Builder.Default
    private Boolean status = true;

    @NotNull(message = "El id de la categoria es obligatorio")
    private Long categoryId;
}
