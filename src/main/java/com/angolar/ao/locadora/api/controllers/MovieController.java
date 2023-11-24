package com.angolar.ao.locadora.api.controllers;


import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Movie;
import com.angolar.ao.locadora.domain.repositories.MovieRepository;
import com.angolar.ao.locadora.domain.service.MovieService;

import org.springframework.beans.BeanUtils;
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

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id_movie}")
    public ResponseEntity<Movie> getById (@PathVariable Long id_movie ) {

        Movie movie = movieRepository.getById(id_movie);

        if ( movie == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .build();
        } 

        return ResponseEntity.ok(movie);
 
    }


    @PostMapping
    public ResponseEntity<?> save (@RequestBody Movie movie ) {

        try {
            Movie entity = movieService.save(movie);
    
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(entity);

        } catch (EntidadeNaoEncontradaException ex ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ex.getMessage());
        }

    }

    @PutMapping("/{id_movie}") 
    public ResponseEntity<?> update (@PathVariable Long id_movie, @RequestBody Movie movie) {

        Optional<Movie> entity = movieRepository.findById(id_movie);

        if ( entity.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

        BeanUtils.copyProperties(movie, entity.get(), "id");
        Movie save = movieService.save(entity.get());
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id_movie}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete (@PathVariable Long id_movie) {

        try {
            movieService.delete(id_movie);

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        } catch ( EntidadeNaoEncontradaException ex ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch ( EntidadeEmUsoException ex ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
       
    }

}
