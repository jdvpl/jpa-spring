package com.jdvpl.backend.controller;

import com.jdvpl.backend.controller.dto.CategoryDTO;
import com.jdvpl.backend.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<List<CategoryDTO>> allCategories() {
        return ResponseEntity.ok(categoryService.allCategories());
    }

    @PostMapping("save")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody @Valid CategoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(dto));
    }

    @DeleteMapping
    public ResponseEntity<Class<Void>> deleteCategory(@RequestParam (required = true) Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Void.class);
    }
}
