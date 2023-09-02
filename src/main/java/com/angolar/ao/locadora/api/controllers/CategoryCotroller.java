package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import com.angolar.ao.locadora.domain.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryCotroller {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> salve( @RequestBody Category category ) {

        Category obj = repository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(obj);

    }

    @GetMapping
    public List<Category> getAll() {

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {

        Optional<Category> category = repository.findById(id);

        if ( category.isPresent() ) {
            return ResponseEntity.ok(category.get());
        }

        return ResponseEntity.notFound()
                .build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category ) {

        Category categoryAtual = repository.findById(id)
                     .orElse(null);

        if ( categoryAtual != null ) {
            BeanUtils.copyProperties(category, categoryAtual, "id");

            categoryAtual = repository.save(categoryAtual);
            return ResponseEntity.ok(categoryAtual);
        }

        return ResponseEntity.notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            service.delete(id);
            return ResponseEntity.noContent()
                    .build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound()
                    .build();
        }  catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
