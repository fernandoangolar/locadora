package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.exception.EntidadeEmUsoException;
import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Actor;
import com.angolar.ao.locadora.domain.repositories.ActorRepository;
import com.angolar.ao.locadora.domain.service.ActorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorRepository repository;

    @Autowired
    private ActorService service;

    @PostMapping
    public ResponseEntity<Actor> salve(@RequestBody Actor actor) {

        Actor obj = repository.save(actor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(obj);
    }

    @GetMapping
    public List<Actor> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getById(@PathVariable Long id) {

        Optional<Actor> actor = repository.findById(id);

        return actor.map(ResponseEntity::ok).orElseGet( () -> ResponseEntity.notFound()
                .build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor actor ) {

        Actor actorActual = repository.findById(id)
                .orElse(null);

        if ( actorActual != null ) {
            BeanUtils.copyProperties(actor, actorActual, "id" );

            actorActual = repository.save(actorActual);
            return  ResponseEntity.ok(actorActual);
        }

        return ResponseEntity.notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            service.delete(id);
            return ResponseEntity.noContent()
                    .build();
        } catch ( EntidadeNaoEncontradaException e ) {
            return ResponseEntity.notFound()
                    .build();
        } catch (EntidadeEmUsoException e ) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());

        }
    }
}
