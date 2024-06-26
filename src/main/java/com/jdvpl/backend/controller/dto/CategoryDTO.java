package com.jdvpl.backend.controller.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "El nombre de la categoría es requerido")
    private String name;

    @NotBlank(message = "La descripción de la categoría es requerida")
    private String description;

    private Boolean status;

    @Builder.Default
    private List<ProductDTO> products = new ArrayList<>();
}
