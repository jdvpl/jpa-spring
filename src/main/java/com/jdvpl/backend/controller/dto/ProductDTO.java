package com.jdvpl.backend.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

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

    @Builder.Default
    private Boolean status = true;

    @NotNull(message = "El id de la categoria es obligatorio")
    private Long categoryId;
}
