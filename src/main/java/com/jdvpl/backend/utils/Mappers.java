package com.jdvpl.backend.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jdvpl.backend.controller.dto.ProductDTO;
import com.jdvpl.backend.controller.dto.ShoppingCartDTO;
import com.jdvpl.backend.controller.dto.ShoppingCartProductDTO;
import com.jdvpl.backend.controller.dto.UserDTO;
import com.jdvpl.backend.repositories.entity.CategoryEntity;
import com.jdvpl.backend.repositories.entity.ProductEntity;
import com.jdvpl.backend.repositories.entity.ShoppingCartEntity;
import com.jdvpl.backend.repositories.entity.ShoppingCartProductEntity;
import com.jdvpl.backend.repositories.entity.UserEntity;

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
                .category(category)
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
                .picture(productEntity.getPicture())
                .status(productEntity.getStatus())
                .categoryId(productEntity.getCategory() != null ? productEntity.getCategory().getId() : null)
                .build();
    }

    public List<ProductDTO> toDTOList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO toDTO(UserEntity user) {
        return UserDTO.builder()
                .name(user.getName() + " " + user.getLastName())
                .email(user.getUsername())
                .build();
    }

    public ShoppingCartDTO toCartDTO(ShoppingCartEntity entity) {
        ShoppingCartDTO cartDTO = new ShoppingCartDTO();
        cartDTO.setIdCart(entity.getId());

        if (entity.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(entity.getUser().getName());
            userDTO.setEmail(entity.getUser().getUsername());
            cartDTO.setUser(userDTO);
        }

        cartDTO.setDateCreated(entity.getDateCreation());
        return cartDTO;
    }

    public ShoppingCartProductDTO toShoppingCartProductDTO(ShoppingCartProductEntity entity) {
        ShoppingCartProductDTO dto = new ShoppingCartProductDTO();
        dto.setId(entity.getId());
        dto.setProduct(toDTO(entity.getProduct()));
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
