package com.jdvpl.backend.services;

import com.jdvpl.backend.controller.dto.ShoppingCartProductDTO;
import com.jdvpl.backend.controller.dto.ShoppingCartDTO;
import com.jdvpl.backend.repositories.ProductRepository;
import com.jdvpl.backend.repositories.ShoppingCartProductRepository;
import com.jdvpl.backend.repositories.ShoppingCartRepository;
import com.jdvpl.backend.repositories.UserRepository;
import com.jdvpl.backend.repositories.entity.ProductEntity;
import com.jdvpl.backend.repositories.entity.ShoppingCartEntity;
import com.jdvpl.backend.repositories.entity.ShoppingCartProductEntity;
import com.jdvpl.backend.repositories.entity.UserEntity;
import com.jdvpl.backend.utils.Mappers;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mappers mappers;

    public ShoppingCartEntity createCart(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        ShoppingCartEntity existingCart = shoppingCartRepository.findByUser(user);
        if (existingCart != null) {
            return existingCart;
        }

        ShoppingCartEntity newCart = new ShoppingCartEntity();
        newCart.setUser(user);
        return shoppingCartRepository.save(newCart);
    }

    public ShoppingCartDTO getCartByUser(UserEntity user) {
        ShoppingCartEntity cartEntity = shoppingCartRepository.findByUser(user);

        if (cartEntity == null) {
            throw new EntityNotFoundException("Cart not found");
        }

        ShoppingCartDTO cartDTO = mappers.toCartDTO(cartEntity);
        List<ShoppingCartProductDTO> productDTOs = cartEntity.getShoppingCartProducts().stream()
                .map(mappers::toShoppingCartProductDTO)
                .collect(Collectors.toList());

        cartDTO.setShoppingCartProducts(productDTOs);

        return cartDTO;
    }

    public void addProductToCart(Long cartId, Long productId, int quantity) {
        ShoppingCartEntity cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ShoppingCartProductEntity newCartItem = new ShoppingCartProductEntity();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(quantity);

        cart.getShoppingCartProducts().add(newCartItem);
        shoppingCartRepository.save(cart);
    }

    public void updateProductQuantity(Long cartId, Long productId, int newQuantity) {
        ShoppingCartEntity cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        ShoppingCartProductEntity cartItem = cart.getShoppingCartProducts().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        cartItem.setQuantity(newQuantity);
        shoppingCartRepository.save(cart);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        ShoppingCartEntity cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        ShoppingCartProductEntity cartItem = cart.getShoppingCartProducts().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        cart.getShoppingCartProducts().remove(cartItem);
        shoppingCartRepository.save(cart);
    }
}
