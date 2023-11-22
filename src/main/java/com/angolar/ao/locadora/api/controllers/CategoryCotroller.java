package com.angolar.ao.locadora.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import com.angolar.ao.locadora.domain.service.CategoryService;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryCotroller {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired 
    private CategoryService categoryService;


    @GetMapping
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> save (@RequestBody Category category) {

        Category categoryAtual = categoryService.save(category);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(categoryAtual);
        
    }


}
