package com.jdvpl.backend.utils;

import com.jdvpl.backend.controller.dto.ProductDTO;
import com.jdvpl.backend.repositories.entity.CategoryEntity;
import com.jdvpl.backend.repositories.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mappers {


    public  ProductEntity toProduct(ProductDTO dto, CategoryEntity category) {
        return ProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                //.category(category)
                .build();
    }

    public  ProductEntity toProduct(ProductDTO dto) {
        return ProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                .build();
    }


    public  ProductDTO toDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .status(productEntity.getStatus())
                //.idCategory(productEntity.getCategory() != null ? productEntity.getCategory().getId() : null)
                .build();
    }

    public List<ProductDTO> toDTOList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
