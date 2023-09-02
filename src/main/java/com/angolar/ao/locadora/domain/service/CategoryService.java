package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void delete( Long id ) {

        Category category = categoryRepository.findById(id).get();

        if ( category == null ) {
            throw new EmptyResultDataAccessException(1);
        }

        categoryRepository.delete(category);

    }
}
