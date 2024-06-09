package com.jdvpl.backend.services;

import com.jdvpl.backend.controller.dto.CategoryDTO;
import com.jdvpl.backend.errors.GeneralException;
import com.jdvpl.backend.repositories.CategoryRepository;
import com.jdvpl.backend.repositories.entity.CategoryEntity;
import com.jdvpl.backend.repositories.entity.ProductEntity;
import com.jdvpl.backend.utils.Mappers;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final Mappers mappers;

    public List<CategoryDTO> allCategories() {
        log.info("Starting method allCategories of CategoryService at "+new Date());
        return categoryRepository.findAll().stream().map(category ->
                CategoryDTO
                        .builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .status(category.getStatus())
                        .products(mappers.toDTOList(category.getProducts()))
                        .build()).collect(Collectors.toList());
    }

    public CategoryDTO categoryById(Long id) {
        log.info("Starting method categoryById of CategoryService at "+new Date());
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryDTO(category.getId(),
                category.getName(),
                category.getDescription(),
                category.getStatus(),
                category.getProducts().stream()
                        .map(mappers::toDTO).collect(Collectors.toList()));
    }

    public CategoryDTO saveCategory(CategoryDTO dto) {
        List<ProductEntity> products = new ArrayList<>();
        CategoryEntity category = null;
        if (dto.getId() != null) {
            category = categoryRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + dto.getId()));
        }
        dto.getProducts().forEach(p -> products.add(mappers.toProduct(p)));
        if (Optional.ofNullable(category).isPresent()) {

            category.getProducts().addAll(products);
        }

        category = CategoryEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .products(products)
                .status(dto.getStatus())
                .build();
        return categoryById(categoryRepository.save(category).getId());

    }

    public void deleteCategory(Long id) throws GeneralException{
        if(categoryRepository.findById(id).isEmpty()){
            throw new GeneralException(HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.value(),
                    "No se encontró una categoría con el id " + id, this.getClass().getName());
        }
        categoryRepository.delete(categoryRepository.findById(id).get());
    }
}
