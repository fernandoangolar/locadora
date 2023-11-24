package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeMovieComMesmoTitulo;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Category;
import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.repositories.CategoryRepository;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.lang.String;

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


    public void delete (Long id_movie) {

        try {
            movieRepository.deleteById(id_movie);
        } catch ( EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadatros de Movie com o código",  id_movie));
        } catch ( DataIntegrityViolationException ex ) {
            throw new EntidadeEmUsoException(String.format("Movie de id %d não pode ser deletado porquê está em uso", id_movie));
        }
    }


    // public Movie buscaOuFalha (Long id_movie) {
    //     return movieRepository.findById(id_movie)
    //             .orElseThrow( () -> new EntidadeNaoEncontradaException(String.format(
    //                     "Não existe cadastro de Movie", id_movie
    //             )) );
    // }

}
