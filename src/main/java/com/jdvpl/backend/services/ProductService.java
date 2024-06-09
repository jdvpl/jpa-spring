package com.jdvpl.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jdvpl.backend.controller.dto.ProductDTO;
import com.jdvpl.backend.errors.GeneralException;
import com.jdvpl.backend.repositories.CategoryRepository;
import com.jdvpl.backend.repositories.entity.CategoryEntity;
import com.jdvpl.backend.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jdvpl.backend.repositories.ProductRepository;
import com.jdvpl.backend.repositories.entity.ProductEntity;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mappers mappers;

    public List<ProductDTO> findAll(){
        return  productRepository.findAll().stream().map(mappers::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> delete(List<Long> ids) throws GeneralException{
        ids.forEach(id -> {
            if(productRepository.findById(id).isEmpty()){
                throw new GeneralException(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(),
                        "No se encontró un producto con el id " + id, this.getClass().getName());
            }
            productRepository.deleteById(id);

        });
        return findAll();
    }

    public List<ProductDTO> saveAllProducts(List<ProductDTO> products) throws GeneralException {
        List<ProductDTO> productsSaved = new ArrayList<>();
        products.forEach(product -> {
            Optional<CategoryEntity> category = categoryRepository.findById(product.getCategoryId());
            if (category.isEmpty()) {
                throw new GeneralException(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(), "No se encontró una categoría con el id " + product.getCategoryId(), this.getClass().getName());
            }
           productsSaved.add(mappers.toDTO(productRepository.save(mappers.toProduct(product, category.get()))));
        });
        return productsSaved;
    }
}
