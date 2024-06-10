package com.jdvpl.backend.controller.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartDTO {

    private Long idCart;
    private UserDTO user;
    private List<ShoppingCartProductDTO> shoppingCartProducts;
    private LocalTime dateCreated;

}
