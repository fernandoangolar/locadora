package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import com.angolar.ao.locadora.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping
    public ResponseEntity<Movie> save (@RequestBody Movie movie ) {

        Movie entity = movieService.save(movie);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }


}
