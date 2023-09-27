package com.angolar.ao.locadora.api.controllers;

import com.angolar.ao.locadora.domain.exception.EntidadeNaoEncontradaException;
import com.angolar.ao.locadora.domain.model.Unidade;
import com.angolar.ao.locadora.domain.repositories.UnidadesRepository;
import com.angolar.ao.locadora.domain.service.UnidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidades")
public class UnidadesController {

    @Autowired
    private UnidadesService service;
    @Autowired
    private UnidadesRepository repository;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Unidade unidades) {

        try {
            Unidade obj = service.salve(unidades);

            return ResponseEntity.ok(obj);
        } catch( EntidadeNaoEncontradaException e ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }


    }

    @GetMapping
    public List<Unidade> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Long id) {

        Optional<Unidade> unidades = repository.findById(id);

        return unidades.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound()
                .build());

    }
}
