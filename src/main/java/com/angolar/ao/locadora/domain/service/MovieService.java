package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.exception.EntidadeMovieComMesmoTitulo;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Movie save ( Movie movie ) {

        if ( movieRepository.existsByTitle(movie.getTitle()) ) {
            throw new EntidadeMovieComMesmoTitulo(String.format("Movies com mesmo Title"));
        }

        Long categoryId = movie.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(String.format(
                    "A Categoria de %d não foi encontrada", categoryId
                )) );

        movie.setCategory(category);
        return  movieRepository.save(movie);
    }

}
