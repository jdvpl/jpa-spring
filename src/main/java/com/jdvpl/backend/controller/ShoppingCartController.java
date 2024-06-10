package com.jdvpl.backend.controller;

import com.jdvpl.backend.controller.dto.CreateCartRequestDTO;
import com.jdvpl.backend.controller.dto.ProductCartRequestDTO;
import com.jdvpl.backend.controller.dto.ShoppingCartDTO;
import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.ShoppingCartEntity;
import com.jdvpl.backend.repositories.entity.UserEntity;
import com.jdvpl.backend.services.ShoppingCartService;
import com.jdvpl.backend.utils.Mappers;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mappers mappers;

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createCart(@RequestBody CreateCartRequestDTO request) {
        Long userId = request.getUserId();
        ShoppingCartEntity cartEntity = shoppingCartService.createCart(userId);
        ShoppingCartDTO cartDTO = mappers.toCartDTO(cartEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCartDTO> getCart(@PathVariable Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ShoppingCartDTO cartDTO = shoppingCartService.getCartByUser(user);

        if (cartDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/{cartId}/products")
    public ResponseEntity<Void> addProductToCart(@PathVariable Long cartId, @RequestBody ProductCartRequestDTO request) {
        shoppingCartService.addProductToCart(cartId, request.getIdProduct(), request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{cartId}/products/{productId}/{quantity}")
    public ResponseEntity<Void> updateProductQuantity(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @PathVariable int quantity) {

        shoppingCartService.updateProductQuantity(cartId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        shoppingCartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }
}
