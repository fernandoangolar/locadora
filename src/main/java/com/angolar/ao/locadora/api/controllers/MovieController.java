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
    private MovieRepository repository;

    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<?> salve(@RequestBody Movie movie) {

        try {
            movie = service.salve(movie);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(movie);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @GetMapping
    public List<Movie> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {

        Optional<Movie> movie = repository.findById(id);

        return movie.map(ResponseEntity::ok).orElseGet( () -> ResponseEntity.notFound()
                .build());
    }
}
