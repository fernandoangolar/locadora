package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Movie salve( Movie movie ) {

        Long categoryId = movie.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException( String.format("Não existe cadastro de categoria com este código %d", categoryId)));

        movie.setCategory(category);
        return repository.save(movie);
    }
}
