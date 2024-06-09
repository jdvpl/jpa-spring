package com.jdvpl.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.jdvpl.backend.controller.dto.ProductDTO;
import com.jdvpl.backend.errors.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jdvpl.backend.services.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productsService.findAll();
        return products.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.ok(products);
    }


    @PostMapping("${api.path.admin}")
    public ResponseEntity<List<ProductDTO>> create(@RequestBody @Valid List<ProductDTO> product) throws GeneralException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveAllProducts(product));
    }

    @DeleteMapping("${api.path.admin}")
    public ResponseEntity<List<ProductDTO>> delete (@RequestBody @Valid List<ProductDTO> products,
                                                    @RequestHeader ("Authorization") String auth) throws GeneralException {
        return ResponseEntity.ok(productsService
                .delete(products
                        .stream()
                        .map(ProductDTO::getId)
                        .collect(Collectors.toList())));
    }
}
