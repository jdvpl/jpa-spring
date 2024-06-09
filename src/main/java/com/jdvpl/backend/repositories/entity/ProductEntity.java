package com.jdvpl.backend.repositories.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @DecimalMin(value="0.01")
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int quantity;

    @Column(columnDefinition = "boolean default true")
    private Boolean status;

    @ManyToOne
    private CategoryEntity category;

}
