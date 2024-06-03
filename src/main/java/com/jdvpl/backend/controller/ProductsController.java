package com.jdvpl.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdvpl.backend.repositories.entity.ProductEntity;
import com.jdvpl.backend.services.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productsRepository;

    // @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll(){
        List<ProductEntity> products =  (List<ProductEntity>) productsRepository.findAll();
        if(products!=null && !products.isEmpty()){
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<ProductEntity> createOne(@RequestBody @Valid ProductEntity product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(product));
    }
}
