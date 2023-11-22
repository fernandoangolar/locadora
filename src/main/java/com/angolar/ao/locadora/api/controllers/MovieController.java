package com.angolar.ao.locadora.api.controllers;


import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import com.angolar.ao.locadora.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getById (@PathVariable Long id ) {

        Movie movie = movieService.buscaOuFalha(id);
        return movie;
    }


    @PostMapping
    public ResponseEntity<Movie> save (@RequestBody Movie movie ) {

        Movie entity = movieService.save(movie);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {

        Movie movie = movieService.buscaOuFalha(id);
        movieService.delete(movie);
    }

}
