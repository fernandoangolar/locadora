package com.angolar.ao.locadora.domain.service;

import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.model.Unidades;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import com.angolar.ao.locadora.domain.repositories.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnidadesService {

    @Autowired
    private UnidadesRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    public Unidades salve( Unidades unidades ) {

        Long id = unidades.getMovie().getId();
        Movie movie = movieRepository.findById(id)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de movie com este id", id)));


        unidades.setMovie(movie);
        return repository.save(unidades);
    }

}
