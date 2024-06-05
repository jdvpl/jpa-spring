package com.jdvpl.backend.services;

import java.util.ArrayList;
import java.util.List;

import com.jdvpl.backend.controller.dto.ProductDTO;
import com.jdvpl.backend.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.repositories.ProductRepository;
import com.jdvpl.backend.repositories.entity.ProductEntity;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private Mappers mappers;

    public List<ProductEntity> findAll(){
        return (List<ProductEntity>) productRepository.findAll();
    }
    public ProductEntity save(ProductEntity personEntity){
        return  productRepository.save(personEntity);
    }
    public String delete(Long id){
        if(productRepository.findById(id).isEmpty()){
            return "No existe registro con este id";
        }
        productRepository.deleteById(id);
        return  "Registro eliminado";
    }

    public List<ProductDTO> saveAllProducts(List<ProductEntity> entities){
        List<ProductDTO> products = new ArrayList<>();
        entities.forEach(e -> {
            products.add(mappers.toDTO(productRepository.save(e)));
        });
        return products;
    }
}
