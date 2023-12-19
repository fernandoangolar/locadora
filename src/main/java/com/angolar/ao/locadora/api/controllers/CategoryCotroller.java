package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import com.angolar.ao.locadora.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
